package ru.netology.page;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$$;

public class StartPage {
    public static BuyPage buyPage() {
        $$("button").find(exactText("Купить")).click();
        return new BuyPage();
    }
}
