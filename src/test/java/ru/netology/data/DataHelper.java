package ru.netology.data;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import lombok.Value;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    public static Faker faker = new Faker(new Locale("en"));

    @Value
    public static class PurchaseInfo {
        String cardNumber;
        String month;
        String year;
        String owner;
        String code;
    }

    // Получение текущего месяца
    public static String getCurrentMonth() {
        LocalDate localDate = LocalDate.now();
        return String.format("%02d", localDate.getMonthValue());
    }

    // Получение следующего года
    public static String getNextYear() {
        LocalDate localDate = LocalDate.now();
        return String.format("%ty", localDate.plusYears(1));
    }

    // Генерация валидного имени
    public static String getValidName() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    // Генерация CVC с двумя цифрами
    public static String getCvcWithTwoDigits() {
        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en"), new RandomService());
        return fakeValuesService.numerify("##");
    }

    // Генерация CVC с одной цифрой
    public static String getCvcWithOneDigit() {
        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en"), new RandomService());
        return fakeValuesService.numerify("#");
    }

    // Генерация валидного CVC
    public static String getValidCvc() {
        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en"), new RandomService());
        return fakeValuesService.numerify("###");
    }

    // Генерация имени с одной буквой
    public static String getNameWithOneLetter() {
        return faker.lorem().characters(1);
    }

    // Генерация имени с числами
    public static String getNameWithNumbers() {
        return faker.number().digits(7);
    }

    // Генерация слишком длинного имени
    public static String getTooLongName() {
        return faker.lorem().fixedString(3000);
    }

    // Генерация фамилии на латинице
    public static String getOnlySurnameInLatinLetters() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().lastName();
    }

    // Генерация фамилии на русском
    public static String getOnlySurname() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().lastName();
    }

    // Генерация имени на латинице
    public static String getOnlyNameInLatinLetters() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().firstName();
    }

    // Генерация имени на русском
    public static String getOnlyName() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().firstName();
    }

    // Генерация прошлого года
    public static String getLastYear() {
        LocalDate localDate = LocalDate.now();
        return String.format("%ty", localDate.minusYears(1));
    }

    // Генерация прошлого месяца
    public static String getLastMonth() {
        LocalDate localDate = LocalDate.now();
        LocalDate lastMonth = localDate.minusMonths(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
        String monthValue = lastMonth.format(formatter);
        return monthValue;
    }

    // Получение текущего года
    public static String getCurrentYear() {
        return String.format("%ty", Year.now());
    }

    // Вынесенные данные для использования в коде
    public static String getApprovedNumber() {
        return System.getenv("APPROVED_CARD_NUMBER"); // Берем из переменной окружения
    }

    public static String getShortCardNumber() {
        return System.getenv("SHORT_CARD_NUMBER"); // Берем из переменной окружения
    }

    public static String getInvalidCardNumber() {
        return System.getenv("INVALID_CARD_NUMBER"); // Берем из переменной окружения
    }

    public static String getDeclinedNumber() {
        return System.getenv("DECLINED_CARD_NUMBER"); // Берем из переменной окружения
    }

    public static String getValidNameInLatinLetters() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().firstName() + " " + faker.name().lastName();
    }
}
