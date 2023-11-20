package data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;

import java.time.format.DateTimeFormatter;


public class Datahelper {

    private static final Faker faker = new Faker(new Locale("en"));
    private static final Random random = new Random();

     private Datahelper() {

    }

    public static String getValidCard1() {
        return "4444444444444441";
    }

    public static String getValidCard2() {
        return "44444444444442";

    }

    public static String getCardNumberLessThan16() {
        return "4444 4444 4444 444";
    }

    public static String getLocalMonth() {
        LocalDate currentData = LocalDate.now();
        return currentData.format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getLocalYear() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getLocalYearPlus50() {
        int localYear = Integer.parseInt(getLocalYear());
        int yearPlus6 = localYear + 50;
        return String.format("%02d", yearPlus6 % 100);
    }

    public static String getPreviousYear() {
        int localYear = Integer.parseInt(getLocalYear());
        int previousYear = localYear - 1;
        return String.format("%02d", previousYear % 100);
    }

    public static String getCvv() {
        return faker.number().digits(3);
    }

    public static String get2Cvv() {
        return faker.number().digits(2);
    }

    public static String getUser() {
        return faker.name().firstName() + faker.name().lastName();
    }

    public static String getNameOnly() {
        return faker.name().firstName();
    }

    public static String getLastNameOnly() {
        return faker.name().lastName();
    }

    public static String getUserInCyrillic() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().firstName() + faker.name().lastName();
    }

    public static String getEmptyCardNumber() {
        return "";
    }

    public static String getEmptyMonth() {
        return "";
    }

    public static String getEmptyYear() {
        return "";
    }

    public static String getEmptyName() {
        return "";
    }

    public static String getEmptyCvv() {
        return "";
    }


}
