package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.Text;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$;

public class TourPage {
    private SelenideElement heading = $$("h2").find(exactText("Путешествие дня"));
    private SelenideElement buttonPurchase = $$("button").findBy(exactText("Купить"));
    private SelenideElement buttonCreditPurchase = $$("button").findBy(exactText("Купить в кредит"));
    private SelenideElement cardNumber = $$(".input__inner").findBy(text("Номер карты")).$(".input__control");
    private SelenideElement cardMonth = $$(".input__inner").findBy(text("Месяц")).$(".input__control");
    private SelenideElement cardYear = $$(".input__inner").findBy(text("Год")).$(".input__control");
    private SelenideElement cardOwner = $$(".input__inner").findBy(text("Владелец")).$(".input__control");
    private SelenideElement cardCVV = $$(".input__inner").findBy(text("CVC/CVV")).$(".input__control");
    private SelenideElement continueButton = $$("button").find(exactText("Продолжить"));
    private SelenideElement statusApproved = $$(".notification__title").find(exactText("Успешно"));
    private SelenideElement statusDenied = $$(".notification__content").find(exactText("Ошибка! Банк отказал в проведении операции."));
    private SelenideElement incorrectFormat = $$("span.input__sub").find(exactText("Неверный формат"));
    private SelenideElement cardExpired = $$("span.input__sub").find(exactText("Истёк срок действия карты"));
    private SelenideElement surelySigned = $$(".input__inner span.input__sub").find(exactText("Поле обязательно для заполнения"));
    ;
    private SelenideElement cardYear2 = $$("span.input__sub").find(exactText("Неверно указан срок действия карты"));
    private SelenideElement invalidMonth = $$("span.input__sub").find(exactText("Неверно указан срок действия карты"));


    public void debitCard() {
        heading.shouldBe(visible);
        buttonPurchase.click();
    }

    public void creditWithCard() {
        heading.shouldBe(visible);
        buttonCreditPurchase.click();
    }

    public void cardNumber(String number) {
        cardNumber.setValue(number);
    }

    public void cardMonth(String month) {
        cardMonth.setValue(month);
    }

    public void cardYear(String year) {
        cardYear.setValue(year);
    }

    public void cardOwner(String owner) {
        cardOwner.setValue(owner);
    }

    public void cardCVV(String cvv) {
        cardCVV.setValue(cvv);
    }

    public void clickButtonContinue() {
        continueButton.click();
    }

    public void statusApproved() {
        statusApproved.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void statusDenied() {
        statusDenied.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void incorrectFormat() {
        incorrectFormat.shouldBe(visible);
    }

    public void cardExpired() {
        cardExpired.shouldBe(visible);
    }

    public void surelySigned() {
        surelySigned.shouldBe(visible);
    }
    public void invalidMonth (){invalidMonth.shouldBe(visible);
    }

    public void cardYear2() {
        cardYear2.shouldBe(visible);
    }

    }





