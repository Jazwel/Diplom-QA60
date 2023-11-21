package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.Datahelper;
import data.SQLHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import page.TourPage;

import static com.codeborne.selenide.Selenide.open;
import static data.SQLHelper.getOrderCount;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class PurchaseTourTest {
    public static String url = System.getProperty("sut.url");
    TourPage tourpage = new TourPage();

    @BeforeEach
    public void openPage() {
        open(url);
    }

    @BeforeAll
    static void setAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDown() {
        SelenideLogger.removeListener("allure");
    }

    @AfterEach
    public void cleanDataBase() {
        SQLHelper.cleanDatabase();
    }

    @Test
    public void shouldUseValidCardOne() {
        tourpage.debitCard();
        tourpage.cardNumber(Datahelper.getValidCard1());
        tourpage.cardMonth(Datahelper.getLocalMonth());
        tourpage.cardYear(Datahelper.getLocalYear());
        tourpage.cardOwner(Datahelper.getUser());
        tourpage.cardCVV(Datahelper.getCvv());
        tourpage.clickButtonContinue();
        tourpage.statusApproved();
        assertEquals("APPROVED", SQLHelper.getDebitStatus());
    }

    @Test
    public void shouldUseValidCardTwo() {
        tourpage.debitCard();
        tourpage.cardNumber(Datahelper.getValidCard2());
        tourpage.cardMonth(Datahelper.getLocalMonth());
        tourpage.cardYear(Datahelper.getLocalYear());
        tourpage.cardOwner(Datahelper.getUser());
        tourpage.cardCVV(Datahelper.getCvv());
        tourpage.clickButtonContinue();
        tourpage.statusDenied();
        assertEquals("DECLINED", SQLHelper.getDebitStatus());
    }

    @Test
    public void shouldUseValidCardOneForCredit() {
        tourpage.creditWithCard();
        tourpage.cardNumber(Datahelper.getValidCard1());
        tourpage.cardMonth(Datahelper.getLocalMonth());
        tourpage.cardYear(Datahelper.getLocalYear());
        tourpage.cardOwner(Datahelper.getUser());
        tourpage.cardCVV(Datahelper.getCvv());
        tourpage.clickButtonContinue();
        tourpage.statusApproved();
        assertEquals("APPROVED", SQLHelper.getCreditStatus());
    }

    @Test
    public void shouldUseValidCardTwoForCredit() {
        tourpage.creditWithCard();
        tourpage.cardNumber(Datahelper.getValidCard2());
        tourpage.cardMonth(Datahelper.getLocalMonth());
        tourpage.cardYear(Datahelper.getLocalYear());
        tourpage.cardOwner(Datahelper.getUser());
        tourpage.cardCVV(Datahelper.getCvv());
        tourpage.clickButtonContinue();
        tourpage.statusDenied();
        assertEquals("DECLINED", SQLHelper.getCreditStatus());
    }

    @Test
    public void shouldUseLessThen16Digits() {
        tourpage.debitCard();
        tourpage.cardNumber(Datahelper.getCardNumberLessThan16());
        tourpage.cardMonth(Datahelper.getLocalMonth());
        tourpage.cardYear(Datahelper.getLocalYear());
        tourpage.cardOwner(Datahelper.getUser());
        tourpage.cardCVV(Datahelper.getCvv());
        tourpage.clickButtonContinue();
        tourpage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void shouldUseIncorrectMonth() {
        tourpage.debitCard();
        tourpage.cardNumber(Datahelper.getValidCard1());
        tourpage.cardMonth(Datahelper.getIncorrectMonth());
        tourpage.cardYear(Datahelper.getLocalYear());
        tourpage.cardOwner(Datahelper.getUser());
        tourpage.cardCVV(Datahelper.getCvv());
        tourpage.clickButtonContinue();
        tourpage.invalidMonth();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void shouldUseExpireYear() {
        tourpage.debitCard();
        tourpage.cardNumber(Datahelper.getValidCard1());
        tourpage.cardMonth(Datahelper.getLocalMonth());
        tourpage.cardYear(Datahelper.getPreviousYear());
        tourpage.cardOwner(Datahelper.getUser());
        tourpage.cardCVV(Datahelper.getCvv());
        tourpage.clickButtonContinue();
        tourpage.cardExpired();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void shouldUseFutureYear() {
        tourpage.debitCard();
        tourpage.cardNumber(Datahelper.getValidCard1());
        tourpage.cardMonth(Datahelper.getLocalMonth());
        tourpage.cardYear(Datahelper.getLocalYearPlus50());
        tourpage.cardOwner(Datahelper.getUser());
        tourpage.cardCVV(Datahelper.getCvv());
        tourpage.clickButtonContinue();
        tourpage.cardYear2();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void shouldUseIncorrectCvv() {
        tourpage.debitCard();
        tourpage.cardNumber(Datahelper.getValidCard1());
        tourpage.cardMonth(Datahelper.getLocalMonth());
        tourpage.cardYear(Datahelper.getLocalYear());
        tourpage.cardOwner(Datahelper.getUser());
        tourpage.cardCVV(Datahelper.get2Cvv());
        tourpage.clickButtonContinue();
        tourpage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void shouldUseOnlyName() {
        tourpage.debitCard();
        tourpage.cardNumber(Datahelper.getValidCard1());
        tourpage.cardMonth(Datahelper.getLocalMonth());
        tourpage.cardYear(Datahelper.getLocalYear());
        tourpage.cardOwner(Datahelper.getNameOnly());
        tourpage.cardCVV(Datahelper.getCvv());
        tourpage.clickButtonContinue();
        tourpage.surelySigned();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void shouldUseOnlyLastName() {
        tourpage.debitCard();
        tourpage.cardNumber(Datahelper.getValidCard1());
        tourpage.cardMonth(Datahelper.getLocalMonth());
        tourpage.cardYear(Datahelper.getLocalYear());
        tourpage.cardOwner(Datahelper.getLastNameOnly());
        tourpage.cardCVV(Datahelper.getCvv());
        tourpage.clickButtonContinue();
        tourpage.surelySigned();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void shouldUseNameInCyrillic() {
        tourpage.debitCard();
        tourpage.cardNumber(Datahelper.getValidCard1());
        tourpage.cardMonth(Datahelper.getLocalMonth());
        tourpage.cardYear(Datahelper.getLocalYear());
        tourpage.cardOwner(Datahelper.getUserNameInCyrillic());
        tourpage.cardCVV(Datahelper.getCvv());
        tourpage.clickButtonContinue();
        tourpage.surelySigned();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void shouldUseLastNameInCyrillic() {
        tourpage.debitCard();
        tourpage.cardNumber(Datahelper.getValidCard1());
        tourpage.cardMonth(Datahelper.getLocalMonth());
        tourpage.cardYear(Datahelper.getLocalYear());
        tourpage.cardOwner(Datahelper.getUserLastNameInCyrillic());
        tourpage.cardCVV(Datahelper.getCvv());
        tourpage.clickButtonContinue();
        tourpage.surelySigned();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void shouldUseEmptyCard() {
        tourpage.debitCard();
        tourpage.cardNumber(Datahelper.getEmptyCardNumber());
        tourpage.cardMonth(Datahelper.getLocalMonth());
        tourpage.cardYear(Datahelper.getLocalYear());
        tourpage.cardOwner(Datahelper.getUser());
        tourpage.cardCVV(Datahelper.getCvv());
        tourpage.clickButtonContinue();
        tourpage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void shouldUseEmptyMonth() {
        tourpage.debitCard();
        tourpage.cardNumber(Datahelper.getValidCard1());
        tourpage.cardMonth(Datahelper.getEmptyMonth());
        tourpage.cardYear(Datahelper.getLocalYear());
        tourpage.cardOwner(Datahelper.getUser());
        tourpage.cardCVV(Datahelper.getCvv());
        tourpage.clickButtonContinue();
        tourpage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void shouldUseEmptyYear() {
        tourpage.debitCard();
        tourpage.cardNumber(Datahelper.getValidCard1());
        tourpage.cardMonth(Datahelper.getLocalMonth());
        tourpage.cardYear(Datahelper.getEmptyYear());
        tourpage.cardOwner(Datahelper.getUser());
        tourpage.cardCVV(Datahelper.getCvv());
        tourpage.clickButtonContinue();
        tourpage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void shouldUseEmptyOwner() {
        tourpage.debitCard();
        tourpage.cardNumber(Datahelper.getValidCard1());
        tourpage.cardMonth(Datahelper.getLocalMonth());
        tourpage.cardYear(Datahelper.getLocalYear());
        tourpage.cardOwner(Datahelper.getEmptyName());
        tourpage.cardCVV(Datahelper.getCvv());
        tourpage.clickButtonContinue();
        tourpage.surelySigned();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void shouldUseEmptyCvv() {
        tourpage.debitCard();
        tourpage.cardNumber(Datahelper.getValidCard1());
        tourpage.cardMonth(Datahelper.getLocalMonth());
        tourpage.cardYear(Datahelper.getLocalYear());
        tourpage.cardOwner(Datahelper.getUser());
        tourpage.cardCVV(Datahelper.getEmptyCvv());
        tourpage.clickButtonContinue();
        tourpage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void shouldUseLessThen16DigitsCredit() {
        tourpage.creditWithCard();
        tourpage.cardNumber(Datahelper.getCardNumberLessThan16());
        tourpage.cardMonth(Datahelper.getLocalMonth());
        tourpage.cardYear(Datahelper.getLocalYear());
        tourpage.cardOwner(Datahelper.getUser());
        tourpage.cardCVV(Datahelper.getCvv());
        tourpage.clickButtonContinue();
        tourpage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void shouldUseIncorrectMonthCredit() {
        tourpage.creditWithCard();
        tourpage.cardNumber(Datahelper.getValidCard1());
        tourpage.cardMonth(Datahelper.getIncorrectMonth());
        tourpage.cardYear(Datahelper.getLocalYear());
        tourpage.cardOwner(Datahelper.getUser());
        tourpage.cardCVV(Datahelper.getCvv());
        tourpage.clickButtonContinue();
        tourpage.invalidMonth();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void shouldUseExpireYearCredit() {
        tourpage.creditWithCard();
        tourpage.cardNumber(Datahelper.getValidCard1());
        tourpage.cardMonth(Datahelper.getLocalMonth());
        tourpage.cardYear(Datahelper.getPreviousYear());
        tourpage.cardOwner(Datahelper.getUser());
        tourpage.cardCVV(Datahelper.getCvv());
        tourpage.clickButtonContinue();
        tourpage.cardExpired();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void shouldUseFutureYearCredit() {
        tourpage.creditWithCard();
        tourpage.cardNumber(Datahelper.getValidCard1());
        tourpage.cardMonth(Datahelper.getLocalMonth());
        tourpage.cardYear(Datahelper.getLocalYearPlus50());
        tourpage.cardOwner(Datahelper.getUser());
        tourpage.cardCVV(Datahelper.getCvv());
        tourpage.clickButtonContinue();
        tourpage.cardYear2();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void shouldUseIncorrectCvvCredit() {
        tourpage.creditWithCard();
        tourpage.cardNumber(Datahelper.getValidCard1());
        tourpage.cardMonth(Datahelper.getLocalMonth());
        tourpage.cardYear(Datahelper.getLocalYear());
        tourpage.cardOwner(Datahelper.getUser());
        tourpage.cardCVV(Datahelper.get2Cvv());
        tourpage.clickButtonContinue();
        tourpage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void shouldUseOnlyNameCredit() {
        tourpage.creditWithCard();
        tourpage.cardNumber(Datahelper.getValidCard1());
        tourpage.cardMonth(Datahelper.getLocalMonth());
        tourpage.cardYear(Datahelper.getLocalYear());
        tourpage.cardOwner(Datahelper.getNameOnly());
        tourpage.cardCVV(Datahelper.getCvv());
        tourpage.clickButtonContinue();
        tourpage.surelySigned();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void shouldUseOnlyLastNameCredit() {
        tourpage.creditWithCard();
        tourpage.cardNumber(Datahelper.getValidCard1());
        tourpage.cardMonth(Datahelper.getLocalMonth());
        tourpage.cardYear(Datahelper.getLocalYear());
        tourpage.cardOwner(Datahelper.getLastNameOnly());
        tourpage.cardCVV(Datahelper.getCvv());
        tourpage.clickButtonContinue();
        tourpage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void shouldUseNameInCyrillicCredit() {
        tourpage.creditWithCard();
        tourpage.cardNumber(Datahelper.getValidCard1());
        tourpage.cardMonth(Datahelper.getLocalMonth());
        tourpage.cardYear(Datahelper.getLocalYear());
        tourpage.cardOwner(Datahelper.getUserNameInCyrillic());
        tourpage.cardCVV(Datahelper.getCvv());
        tourpage.clickButtonContinue();
        tourpage.surelySigned();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void shouldUseLastNameInCyrillicCredit() {
        tourpage.creditWithCard();
        tourpage.cardNumber(Datahelper.getValidCard1());
        tourpage.cardMonth(Datahelper.getLocalMonth());
        tourpage.cardYear(Datahelper.getLocalYear());
        tourpage.cardOwner(Datahelper.getUserLastNameInCyrillic());
        tourpage.cardCVV(Datahelper.getCvv());
        tourpage.clickButtonContinue();
        tourpage.surelySigned();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void shouldUseEmptyCardCredit() {
        tourpage.creditWithCard();
        tourpage.cardNumber(Datahelper.getEmptyCardNumber());
        tourpage.cardMonth(Datahelper.getLocalMonth());
        tourpage.cardYear(Datahelper.getLocalYear());
        tourpage.cardOwner(Datahelper.getUser());
        tourpage.cardCVV(Datahelper.getCvv());
        tourpage.clickButtonContinue();
        tourpage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void shouldUseEmptyMonthCredit() {
        tourpage.creditWithCard();
        tourpage.cardNumber(Datahelper.getValidCard1());
        tourpage.cardMonth(Datahelper.getEmptyMonth());
        tourpage.cardYear(Datahelper.getLocalYear());
        tourpage.cardOwner(Datahelper.getUser());
        tourpage.cardCVV(Datahelper.getCvv());
        tourpage.clickButtonContinue();
        tourpage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void shouldUseEmptyYearCredit() {
        tourpage.creditWithCard();
        tourpage.cardNumber(Datahelper.getValidCard1());
        tourpage.cardMonth(Datahelper.getLocalMonth());
        tourpage.cardYear(Datahelper.getEmptyYear());
        tourpage.cardOwner(Datahelper.getUser());
        tourpage.cardCVV(Datahelper.getCvv());
        tourpage.clickButtonContinue();
        tourpage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void shouldUseEmptyOwnerCredit() {
        tourpage.creditWithCard();
        tourpage.cardNumber(Datahelper.getValidCard1());
        tourpage.cardMonth(Datahelper.getLocalMonth());
        tourpage.cardYear(Datahelper.getLocalYear());
        tourpage.cardOwner(Datahelper.getEmptyName());
        tourpage.cardCVV(Datahelper.getCvv());
        tourpage.clickButtonContinue();
        tourpage.surelySigned();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void shouldUseEmptyCvvCredit() {
        tourpage.creditWithCard();
        tourpage.cardNumber(Datahelper.getValidCard1());
        tourpage.cardMonth(Datahelper.getLocalMonth());
        tourpage.cardYear(Datahelper.getLocalYear());
        tourpage.cardOwner(Datahelper.getUser());
        tourpage.cardCVV(Datahelper.getEmptyCvv());
        tourpage.clickButtonContinue();
        tourpage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }
}
