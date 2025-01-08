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

    public static String getCurrentMonth() {
        LocalDate localDate = LocalDate.now();
        return String.format("%02d", localDate.getMonthValue());
    }

    public static String getNextYear() {
        LocalDate localDate = LocalDate.now();
        return String.format("%ty", localDate.plusYears(1));
    }

    public static String getValidName() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getCvcWithTwoDigits() {
        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en"), new RandomService());
        return fakeValuesService.numerify("##");
    }

    public static String getCvcWithOneDigit() {
        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en"), new RandomService());
        return fakeValuesService.numerify("#");
    }

    public static String getValidCvc() {
        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en"), new RandomService());
        return fakeValuesService.numerify("###");
    }

    public static String getNameWithOneLetter() {
        return faker.lorem().characters(1);
    }

    public static String getNameWithNumbers() {
        return faker.number().digits(7);
    }

    public static String getTooLongName() {
        return faker.lorem().fixedString(3000);
    }

    public static String getOnlySurnameInLatinLetters() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().lastName();
    }

    public static String getOnlySurname() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().lastName();
    }

    public static String getOnlyNameInLatinLetters() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().firstName();
    }

    public static String getOnlyName() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().firstName();
    }

    public static String getLastYear() {
        LocalDate localDate = LocalDate.now();
        return String.format("%ty", localDate.minusYears(1));
    }

    public static String getLastMonth() {
        LocalDate localDate = LocalDate.now();
        LocalDate lastMonth = localDate.minusMonths(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
        String monthValue = lastMonth.format(formatter);
        return monthValue;
    }

    public static String getCurrentYear() {
        return String.format("%ty", Year.now());
    }

    public static String getApprovedNumber() {
        return "4444 4444 4444 4441";
    }

    public static String getShortCardNumber() {
        return "1111 2222 3333 444";
    }

    public static String getInvalidCardNumber() {
        return "9999 9999 9999 9999";
    }

    public static String getDeclinedNumber() {
        return "4444 4444 4444 4442";
    }

    public static String getValidNameInLatinLetters() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().firstName() + " " + faker.name().lastName();
    }
}