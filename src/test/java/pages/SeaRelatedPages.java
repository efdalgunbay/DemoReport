package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Helper;
import utils.context.ContextKeys;
import utils.context.ContextMap;

import java.util.List;

public class SeaRelatedPages extends Helper {
    @FindBy(id = "email")
    WebElement emailTxt;
    @FindBy(id = "phone")
    WebElement phoneTxt;
    @FindBy(xpath = "//legend[contains(text(),'Sefer')]")
    WebElement routeInfoTitle;
    @FindBy(css = "[class='safe']")
    WebElement safetyTextClass;
    @FindBy(id = "pay")
    WebElement payButton;
    @FindBy(id = "card-number")
    WebElement cardNumberTxt;
    @FindBy(id = "card-expiration")
    WebElement cardExpirationTxt;
    @FindBy(id = "card-csc")
    WebElement cardCsvTxt;
    @FindBy(id = "card-number-error")
    WebElement cardNumberErrorTxt;
    @FindBy(id = "card-expiration-error")
    WebElement cardExpirationErrorTxt;
    @FindBy(id = "card-csc-error")
    WebElement cardCsvErrorTxt;
    @FindBy(css = "[class='day']")
    WebElement birthDay;
    @FindBy(css = "[class='month']")
    WebElement birthMonth;
    @FindBy(css = "[class='year']")
    WebElement birthYear;
    @FindBy(id = "outbound-journeys")
    List<WebElement> listOfResults;
    @FindBy(css = "[class*='available']")
    List<WebElement> listOfAvailableSeats;
    @FindBy(css = ".selection.open>button")
    WebElement confirmAndContinueBTN;
    @FindBy(css = "[class='eco']")
    WebElement idoEcoClass;
    @FindBy(css = "[class='promo']")
    WebElement idoProClass;
    @FindBy(css = "[class=\"seats\"]>ul>li>img")
    List<WebElement> selectedSeatList;
    @FindBy(css = "[class=\"full text\"]")
    WebElement fullyBooked;


    public void selectRandomTripAndSeat(String company) {
        logger.info("Selecting random trip and seat");
        waitUntilListIsLoad(listOfResults);
        int rnd = getRandomNumber(listOfResults.size());
        try {
            swipeUntilElement(listOfResults.get(rnd));
            waitUntilElementVisibleAndClick(listOfResults.get(rnd));
            if (company.equalsIgnoreCase("ido")) {
                if (idoEcoClass.isDisplayed()) idoEcoClass.click();
                else idoProClass.click();
            }
            waitUntilListIsLoad(listOfAvailableSeats);
            int availableSeatRnd = getRandomNumber(listOfAvailableSeats.size());
            String seaSeatCode = listOfAvailableSeats.get(availableSeatRnd).getAttribute("obilet:seat");
            String seaSeatNmb = listOfAvailableSeats.get(availableSeatRnd).getAttribute("obilet:code");
            if (company.equalsIgnoreCase("budo")) {
                seaSeatNmb = listOfAvailableSeats.get(availableSeatRnd).getAttribute("obilet:code").substring(1);
            }
            ContextMap.addContext(ContextKeys.SEASEATCODE, seaSeatCode);
            ContextMap.addContext(ContextKeys.SEASEATNUMB, seaSeatNmb);
            swipeUntilElement(listOfAvailableSeats.get(availableSeatRnd));
            waitUntilElementVisibleAndClick(listOfAvailableSeats.get(availableSeatRnd));
            //TODO koltuk no vs. kontrol ekle
        } catch (Exception e) {
            logger.info("Error" + e);
        }
    }


    public void clickConfirmAndContinue() {
        logger.info("Clicking confirm button");
        waitUntilElementVisibleAndClick(confirmAndContinueBTN);
    }


    public void setPassengerInfoForSinglePassenger() {
        setEmailAndPhone();
        sendKeyz(emailTxt, userProp.getProperty("EMAIL"));
        sendKeyz(phoneTxt, userProp.getProperty("phoneNumber"));
        WebElement nameSurnameTxt = getDriver().findElement(By.id("name-" + ContextMap.getContextValue(ContextKeys.SEASEATCODE) + ""));
        WebElement tcNoTxt = getDriver().findElement(By.id("gov-id-" + ContextMap.getContextValue(ContextKeys.SEASEATCODE) + ""));
        sendKeyz(nameSurnameTxt, userProp.getProperty("nameSurnameSingle"));
        swipeUntilElement(tcNoTxt).sendKeys(userProp.getProperty("IDSingle"));
        //TODO parameterized that
        swipeUntilElement(birthDay);
        select(birthDay, "9");
        select(birthMonth, "8");
        select(birthYear, "1993");
    }

    public void setCardNumber(String cardType) {
        String creditCard = null;
        String csv = null;
        swipeUntilElement(cardNumberTxt);
        if (cardType.equalsIgnoreCase("dummy")) {
            creditCard = userProp.getProperty("dummyCard");
            csv = userProp.getProperty("dummyCsv");
        } else {
            creditCard = userProp.getProperty("CC");
            csv = userProp.getProperty("realCsv");
        }
        for (int i = 0; i < creditCard.length(); i++) {
            char c = creditCard.charAt(i);
            String credit = new StringBuilder().append(c).toString();
            sendKeyz(cardNumberTxt, credit);
        }
        swipeUntilElement(cardExpirationTxt);
        sendKeyz(cardExpirationTxt, userProp.getProperty("expirationDate"));
        sendKeyz(cardCsvTxt, csv);
    }

    public void clickPayButton() {
        logger.info("Clicking pay button");
        waitUntilElementVisibleAndClick(payButton);
    }
}
