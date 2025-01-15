package ru.netology.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.setup.CoreTestCase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.*;
import static ru.netology.data.DbUtils.getPaymentStatus;
import static ru.netology.page.StartPage.buyPage;

public class ServiceTest extends CoreTestCase {

    @Test
    @Tag("passed")
    @DisplayName("1. passed")
    void buyInPaymentGate() {
        DataHelper.PurchaseInfo purchaseInfo = new DataHelper.PurchaseInfo(getApprovedNumber(), getCurrentMonth(), getNextYear(), getValidName(), getValidCvc());
        buyPage().notificationPage(purchaseInfo);
        buyPage().checkSuccessNotification();
        assertEquals("APPROVED", getPaymentStatus());
    }

    @Test
    @Tag("passed")
    @DisplayName("2. passed")
    void buyInPaymentGateWithNameInLatinLetters() {
        DataHelper.PurchaseInfo purchaseInfo = new DataHelper.PurchaseInfo(getApprovedNumber(), getCurrentMonth(), getNextYear(), getValidNameInLatinLetters(), getValidCvc());
        buyPage().notificationPage(purchaseInfo);
        buyPage().checkSuccessNotification();
        assertEquals("APPROVED", getPaymentStatus());
    }

    @Test
    @Tag("fail")
    @DisplayName("3. DECLINED, DANGER банк одобрил!!!!")
    void notBuyInPaymentGateWithDeclinedCardNumber() {
        DataHelper.PurchaseInfo purchaseInfo = new DataHelper.PurchaseInfo(getDeclinedNumber(), getCurrentMonth(), getNextYear(), getValidName(), getValidCvc());
        buyPage().notificationPage(purchaseInfo);
        buyPage().checkDeclineNotification();
        assertEquals("DECLINED", getPaymentStatus());
    }

    @Test
    @Tag("fail")
    @DisplayName("4. сервис принимает для оплаты карту \"9999 9999 9999 9999\" спустя 15 сек ожид")
    void notBuyInPaymentGateWithInvalidCardNumber() {
        DataHelper.PurchaseInfo purchaseInfo = new DataHelper.PurchaseInfo(getInvalidCardNumber(), getCurrentMonth(), getNextYear(), getValidName(), getValidCvc());
        buyPage().notificationPage(purchaseInfo);
        buyPage().checkDeclineNotification();
    }

    @Test
    @Tag("passed")
    @DisplayName("5. passed")
    void notBuyInPaymentGateWithShortCardNumber() {
        DataHelper.PurchaseInfo purchaseInfo = new DataHelper.PurchaseInfo(getShortCardNumber(), getCurrentMonth(), getNextYear(), getValidName(), getValidCvc());
        buyPage().notificationPage(purchaseInfo);
        buyPage().checkInvalidFormat();
    }

    @Test
    @Tag("passed")
    @DisplayName("6. пустое поле 'карта'")
    void notBuyInPaymentGateWithEmptyCardNumber() {
        DataHelper.PurchaseInfo purchaseInfo = new DataHelper.PurchaseInfo(null, getCurrentMonth(), getNextYear(), getValidName(), getValidCvc());
        buyPage().notificationPage(purchaseInfo);
        buyPage().checkRequiredField();
    }

    @Test
    @Tag("fail")
    @DisplayName("7. неверно указал месяц(00) + год(next) сервис принимает")
    void notBuyInPaymentGateWithInvalidMonth() {
        DataHelper.PurchaseInfo purchaseInfo = new DataHelper.PurchaseInfo(getApprovedNumber(), "00", getNextYear(), getValidName(), getValidCvc());
        buyPage().notificationPage(purchaseInfo);
        buyPage().checkInvalidDate();
    }

    @Test
    @Tag("passed")
    @DisplayName("8. неверно указал месяц(13) + год(прошлый)")
    void notBuyInPaymentGateWithNonExistingMonth() {
        DataHelper.PurchaseInfo purchaseInfo = new DataHelper.PurchaseInfo(getApprovedNumber(), "13", getNextYear(), getValidName(), getValidCvc());
        buyPage().notificationPage(purchaseInfo);
        buyPage().checkInvalidDate();
    }

    @Test
    @Tag("fail")
    @DisplayName("9. неверно указал месяц(прошлый) + год(прошлый)")
    void notBuyInPaymentGateWithExpiredMonth() {
        DataHelper.PurchaseInfo purchaseInfo = new DataHelper.PurchaseInfo(getApprovedNumber(), "12", "24", getValidName(), getValidCvc());
        buyPage().notificationPage(purchaseInfo);
        buyPage().checkInvalidDate();
    }

    @Test
    @Tag("passed")
    @DisplayName("10. отправил пустое поле 'месяц' ")
    void notBuyInPaymentGateWithEmptyMonth() {
        DataHelper.PurchaseInfo purchaseInfo = new DataHelper.PurchaseInfo(getApprovedNumber(), null, getNextYear(), getValidName(), getValidCvc());
        buyPage().notificationPage(purchaseInfo);
        buyPage().checkRequiredField();
    }

    @Test
    @Tag("passed")
    @DisplayName("11. passed")
    void notBuyInPaymentGateWithExpiredYear() {
        DataHelper.PurchaseInfo purchaseInfo = new DataHelper.PurchaseInfo(getApprovedNumber(), getCurrentMonth(), getLastYear(), getValidName(), getValidCvc());
        buyPage().notificationPage(purchaseInfo);
        buyPage().checkExpiredDate();
    }

    @Test
    @Tag("passed")
    @DisplayName("12. отправил пустое поле 'год' ")
    void notBuyInPaymentGateWithEmptyYear() {
        DataHelper.PurchaseInfo purchaseInfo = new DataHelper.PurchaseInfo(getApprovedNumber(), getCurrentMonth(), null, getValidName(), getValidCvc());
        buyPage().notificationPage(purchaseInfo);
        buyPage().checkRequiredField();
    }

    @Test
    @Tag("fail")
    @DisplayName("13. Element not found {.input__sub} владелец может содержать 1 имя Русск")
    void notBuyInPaymentGateWithOnlyName() {
        DataHelper.PurchaseInfo purchaseInfo = new DataHelper.PurchaseInfo(getApprovedNumber(), getCurrentMonth(), getNextYear(), getOnlyName(), getValidCvc());
        buyPage().notificationPage(purchaseInfo);
        buyPage().checkInvalidName();
    }

    @Test
    @Tag("fail")
    @DisplayName("14. Element not found {.input__sub} владелец может содержать 1 имя Латин")
    void notBuyInPaymentGateWithOnlyNameInLatinLetters() {
        DataHelper.PurchaseInfo purchaseInfo = new DataHelper.PurchaseInfo(getApprovedNumber(), getCurrentMonth(), getNextYear(), getOnlyNameInLatinLetters(), getValidCvc());
        buyPage().notificationPage(purchaseInfo);
        buyPage().checkInvalidName();
    }

    @Test
    @Tag("fail")
    @DisplayName("15. Element not found {.input__sub} владелец может содержать 1 фамилию Русск")
    void notBuyInPaymentGateWithOnlySurname() {
        DataHelper.PurchaseInfo purchaseInfo = new DataHelper.PurchaseInfo(getApprovedNumber(), getCurrentMonth(), getNextYear(), getOnlySurname(), getValidCvc());
        buyPage().notificationPage(purchaseInfo);
        buyPage().checkInvalidName();
    }

    @Test
    @Tag("fail")
    @DisplayName("16. Element not found {.input__sub} владелец может содержать 1 фамилию Латин")
    void notBuyInPaymentGateWithOnlySurnameInLatinLetters() {
        DataHelper.PurchaseInfo purchaseInfo = new DataHelper.PurchaseInfo(getApprovedNumber(), getCurrentMonth(), getNextYear(), getOnlySurnameInLatinLetters(), getValidCvc());
        buyPage().notificationPage(purchaseInfo);
        buyPage().checkInvalidName();
    }

    @Test
    @Tag("fail")
    @DisplayName("17. Element not found {.input__sub} владелец 3000 символов")
    void notBuyInPaymentGateWithTooLongName() {
        DataHelper.PurchaseInfo purchaseInfo = new DataHelper.PurchaseInfo(getApprovedNumber(), getCurrentMonth(), getNextYear(), getTooLongName(), getValidCvc());
        buyPage().notificationPage(purchaseInfo);
        buyPage().checkLongName();
    }

    @Test
    @Tag("fail")
    @DisplayName("18. Fail Element not found {.input__sub} владелец цифрами")
    void notBuyInPaymentGateWithDigitsInName() {
        DataHelper.PurchaseInfo purchaseInfo = new DataHelper.PurchaseInfo(getApprovedNumber(), getCurrentMonth(), getNextYear(), getNameWithNumbers(), getValidCvc());
        buyPage().notificationPage(purchaseInfo);
        buyPage().checkInvalidDataName();
    }

    @Test
    @Tag("fail")
    @DisplayName("19. Element not found {.input__sub} \"владелец\" с одним символом и выдает \"успешно\"")
    void notBuyInPaymentGateWithTooShortName() {
        DataHelper.PurchaseInfo purchaseInfo = new DataHelper.PurchaseInfo(getApprovedNumber(), getCurrentMonth(), getNextYear(), getNameWithOneLetter(), getValidCvc());
        buyPage().notificationPage(purchaseInfo);
        buyPage().checkShortName();
    }

    @Test
    @Tag("passed")
    @DisplayName("20. passed")
    void notBuyInPaymentGateWithEmptyName() {
        DataHelper.PurchaseInfo purchaseInfo = new DataHelper.PurchaseInfo(getApprovedNumber(), getCurrentMonth(), getNextYear(), null, getValidCvc());
        buyPage().notificationPage(purchaseInfo);
        buyPage().checkInvalidDataName();
    }

    //
    @Test
    @Tag("passed")
    @DisplayName("21. пропущен владелец")
    void notBuyInPaymentGateWithSpaceInsteadOfName() {
        DataHelper.PurchaseInfo purchaseInfo = new DataHelper.PurchaseInfo(getApprovedNumber(), getCurrentMonth(), getNextYear(), " ", getValidCvc());
        buyPage().notificationPage(purchaseInfo);
        buyPage().checkInvalidDataName();
    }

    @Test
    @Tag("passed")
    @DisplayName("22. Fail 1 ссв")
    void notBuyInPaymentGateWithOneDigitInCvc() {
        DataHelper.PurchaseInfo purchaseInfo = new DataHelper.PurchaseInfo(getApprovedNumber(), getCurrentMonth(), getNextYear(), getValidName(), getCvcWithOneDigit());
        buyPage().notificationPage(purchaseInfo);
        buyPage().checkInvalidCvc();
    }

    @Test
    @Tag("passed")
    @DisplayName("23. Fail 2 ссв")
    void notBuyInPaymentGateWithTwoDigitsInCvc() {
        DataHelper.PurchaseInfo purchaseInfo = new DataHelper.PurchaseInfo(getApprovedNumber(), getCurrentMonth(), getNextYear(), getValidName(), getCvcWithTwoDigits());
        buyPage().notificationPage(purchaseInfo);
        buyPage().checkInvalidCvc();
    }

    @Test
    @Tag("fail")
    @DisplayName("24. Fail, extra input__sub")
    void notBuyInPaymentGateWithEmptyCvc() {
        DataHelper.PurchaseInfo purchaseInfo = new DataHelper.PurchaseInfo(getApprovedNumber(), getCurrentMonth(), getNextYear(), getValidName(), null);
        buyPage().notificationPage(purchaseInfo);
        buyPage().checkRequiredField();
    }

    @Test
    @Tag("failed")
    @DisplayName("25. failed, bc error different ")
    void notBuyInPaymentGateWithAllEmptyFields() {
        DataHelper.PurchaseInfo purchaseInfo = new DataHelper.PurchaseInfo(null, null, null, null, null);
        buyPage().notificationPage(purchaseInfo);
        buyPage().checkAllFieldsAreRequired();
    }
}