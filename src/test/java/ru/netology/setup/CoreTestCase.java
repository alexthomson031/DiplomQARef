package ru.netology.setup;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DbUtils.clearTables;

public class CoreTestCase {
    public static WebDriver driver;

    @BeforeEach
    public void setUp() {
        clearTables();
        WebDriverManager.chromedriver().setup();
        Configuration.browserSize = "1920x1280";
//        Configuration.headless = true;
        Configuration.baseUrl = "http://localhost:8080";
        open("/");
    }

    @AfterEach
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
