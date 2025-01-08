package ru.netology.page;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BuyPage {
    private final SelenideElement numberCardField = $("input.input__control");
    private final SelenideElement monthField = $$("input.input__control").get(1);
    private final SelenideElement yearField = $$("input.input__control").get(2);
    private final SelenideElement ownerField = $$("input.input__control").get(3);
    private final SelenideElement codeField = $$("input.input__control").get(4);
    private final SelenideElement successNotification = $("notification.notification_status_ok.notification__title");
    private final SelenideElement buttonError = $("button.icon.icon_theme_alfa-on-color");
    private final SelenideElement error = $("notification.notification_status_error");
    private final SelenideElement button = $$("button").findBy(Condition.text("Продолжить"));

    public void notificationPage(DataHelper.PurchaseInfo purchaseInfo) {
        numberCardField.setValue(purchaseInfo.getCardNumber());
        monthField.setValue(purchaseInfo.getMonth());
        yearField.setValue(purchaseInfo.getYear());
        ownerField.setValue(purchaseInfo.getOwner());
        codeField.setValue(purchaseInfo.getCode());
        button.click();
    }

    public void checkSuccessNotification() {
        $(".notification_status_ok").shouldBe(Condition.visible, Duration.ofMillis(15000));
    }

    public void checkAllFieldsAreRequired() {
        $$("span.input__sub").shouldHave(CollectionCondition.size(5)).shouldHave(CollectionCondition.texts("Поле обязательно для заполнения"));
    }

    public void checkInvalidCvc() {
        $("span.input__sub").shouldHave(exactText("Значение поля должно содержать 3 цифры")).shouldBe(visible);
    }

    public void checkInvalidDataName() {
        $("span.input__sub").shouldHave(exactText("Поле обязательно для заполнения")).shouldBe(visible);
    }

    public void checkRequiredField() {
        $("span.input__sub").shouldHave(exactText("Неверный формат")).shouldBe(visible);
    }

    public void checkShortName() {
        $("span.input__sub").shouldHave(exactText("Значение поля должно содержать больше одной буквы")).shouldBe(visible);
    }

    public void checkLongName() {
        $("span.input__sub").shouldHave(exactText("Значение поля не может содержать более 100 символов")).shouldBe(visible);
    }

    public void checkInvalidName() {
        $("span.input__sub").shouldHave(exactText("Введите полное имя и фамилию")).shouldBe(visible);
    }

    public void checkExpiredDate() {
        $("span.input__sub").shouldHave(exactText("Истёк срок действия карты")).shouldBe(visible);
    }

    public void checkInvalidDate() {
        $("span.input__sub").shouldHave(exactText("Неверно указан срок действия карты")).shouldBe(visible);
    }

    public void checkInvalidFormat() {
        $("span.input__sub").shouldHave(exactText("Неверный формат")).shouldBe(visible);
    }

    public void checkDeclineNotification() {
        $(".notification__content").shouldHave(exactText("Ошибка! Банк отказал в проведении операции.")).shouldBe(visible, Duration.ofSeconds(15));
    }

    public void checkDeclineNotificationSql() {
        $("notification_status_error").shouldBe(Condition.visible, Duration.ofMillis(15000));
    }
}