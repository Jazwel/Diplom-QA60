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
        tourpage.setCardNumber(Datahelper.getValidCard1());
        tourpage.setCardMonth(Datahelper.getLocalMonth());
        tourpage.setCardYear(Datahelper.getLocalYear());
        tourpage.setCardOwner(Datahelper.getUser());
        tourpage.setCardCVV(Datahelper.getCvv());
        tourpage.clickButtonContinue();
        tourpage.statusApproved();
        assertEquals("APPROVED", SQLHelper.getDebitStatus());
    }

    @Test
    public void shouldUseValidCardTwo() {
        tourpage.debitCard();
        tourpage.setCardNumber(Datahelper.getValidCard2());
        tourpage.setCardMonth(Datahelper.getLocalMonth());
        tourpage.setCardYear(Datahelper.getLocalYear());
        tourpage.setCardOwner(Datahelper.getUser());
        tourpage.setCardCVV(Datahelper.getCvv());
        tourpage.clickButtonContinue();
        tourpage.statusDenied();
        assertEquals("DECLINED", SQLHelper.getDebitStatus());
    }

    @Test
    public void shouldUseValidCardOneForCredit() {
        tourpage.creditWithCard();
        tourpage.setCardNumber(Datahelper.getValidCard1());
        tourpage.setCardMonth(Datahelper.getLocalMonth());
        tourpage.setCardYear(Datahelper.getLocalYear());
        tourpage.setCardOwner(Datahelper.getUser());
        tourpage.setCardCVV(Datahelper.getCvv());
        tourpage.clickButtonContinue();
        tourpage.statusApproved();
        assertEquals("APPROVED", SQLHelper.getCreditStatus());
    }
    @Test
    public void shouldUseValidCardOneForCreditValid2() {
        tourpage.creditWithCard();
        tourpage.setCardNumber(Datahelper.getValidCard2());
        tourpage.setCardMonth(Datahelper.getLocalMonth());
        tourpage.setCardYear(Datahelper.getLocalYear());
        tourpage.setCardOwner(Datahelper.getUser());
        tourpage.setCardCVV(Datahelper.getCvv());
        tourpage.clickButtonContinue();
        tourpage.statusDenied();
        assertEquals("DECLINED", SQLHelper.getCreditStatus());
    }

    @Test
    public void shouldUseLessThen16Digits() {
        tourpage.debitCard();
        tourpage.setCardNumber(Datahelper.getCardNumberLessThan16());
        tourpage.setCardMonth(Datahelper.getLocalMonth());
        tourpage.setCardYear(Datahelper.getLocalYear());
        tourpage.setCardOwner(Datahelper.getUser());
        tourpage.setCardCVV(Datahelper.getCvv());
        tourpage.clickButtonContinue();
        tourpage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }
}