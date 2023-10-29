package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Helper;
import utils.context.ContextKeys;
import utils.context.ContextMap;

import java.util.ArrayList;
import java.util.List;

public class BusRelatedPages extends Helper {
    @FindBy(id = "email")
    WebElement emailTxt;
    @FindBy(id = "phone")
    WebElement phoneTxt;
    @FindBy(css = ".header.with-icon>[class='close']")
    WebElement popUpCloseBtn;
    @FindBy(css = "[id='out-ticket']>tr:nth-child(2)>td:nth-child(3)")
    WebElement pnrCodeTxt;
    @FindBy(css = "[class='partner']>th>h3")
    WebElement partnerName;

    @FindBy(css = "[class*='main']>div>div>img")
    List<WebElement> seferSonuclariImgList;
    @FindBy(css = "[class*=\"list container\"]>li")
    List<WebElement> seferSonuclariList;
    @FindBy(css = "[class=\"seats\"]>ul>li>img")
    List<WebElement> selectedSeatList;
    @FindBy(css = "")
    WebElement selectedTripTime;
    @FindBy(xpath = "//*[@class='container item journey open success']//*[@class='price right col price-top']//span")
    WebElement selectedTripPrice;
    @FindBy(css = "[class*='available active single']")
    List<WebElement> availableSingleSeatsList;
    @FindBy(css = "[class*='available active not-single']")
    List<WebElement> availableDoubleSeatsList;
    @FindBy(css = "[class*='available']")
    List<WebElement> availableSeatsList;
    @FindBy(css = "[class*='available'] [class='s-seat-n']")
    List<WebElement> availableSeatsNumber;
    @FindBy(css = "[class*='proceed']")
    WebElement proceedAndPaymentBtn;
    @FindBy(css = "[class='action toggle']")
    WebElement filterBtn;
    @FindBy(id = "origin-input")
    WebElement whereFromListPage;
    @FindBy(id = "list")
    WebElement listSection;
    @FindBy(xpath = "//*[@class='price']//span")
    WebElement totalAmount;


    public void selectRandomTripBus() {
        waitUntilLoadingIsDone();
        // checkDestinationsFromPageTitle();
        waitUntilListIsLoad(seferSonuclariList);
        int rnd = getRandomNumber(seferSonuclariList.size());
        while (seferSonuclariImgList.get(rnd).getAttribute("alt").contains("Metro")) {
            rnd = getRandomNumber(seferSonuclariImgList.size());
            seferSonuclariImgList.get(rnd);
        }
        try {
            clickMouseAction(seferSonuclariImgList.get(rnd));
            waitUntilListIsLoad(availableSeatsList);
        } catch (Exception e) {
        }
    }

    static int seatCount;
    static List<String> seatNumbersList = new ArrayList<>();

    public void busSelectAvailableSeat(String gender) {
        waitUntilLoadingIsDone();
        if ((availableSeatsList.size() < 4) && (availableSingleSeatsList.size() < 4)) {
            selectRandomTripBus();
        }
        waitUntilListIsLoad(availableSeatsList);
        int rnd = getRandomNumber(availableSingleSeatsList.size());
        waitUntilElementVisibleAndClick(availableSingleSeatsList.get(rnd));
        WebElement setGender = getDriver().findElement(By.cssSelector("[class='" + gender + " ']"));
        waitUntilElementClickable(setGender);
        waitUntilElementVisibleAndClick(setGender);
        seatCount += 1;
        ContextMap.addContext(ContextKeys.SEATCOUNT, String.valueOf(seatCount));
    }

    public void addContextSelectedSeatsList() {
        if (seatCount > 0) {
            for (int i = 0; i < seatCount; i++) {
                if (Integer.parseInt(selectedSeatList.get(i).getAttribute("alt")) != 0) {
                    seatNumbersList.add(i, selectedSeatList.get(i).getAttribute("alt"));
                }
            }
        }
    }


    public void checkDestinationsFromPageTitle() {
        // waitUntilElementVisible(filterBtn);
        // String titleExpected = (HomePageAll.tripModel.getWhereFrom().split("-")[0] + " - " + HomePageAll.tripModel.getWhereTo().split("-")[0]);
        // String titleActual = getTitle();
        //  Assert.assertTrue("Listelenen seferler uyumsuz : " + "Expected Title : " + titleExpected + "  Actual Title : " + titleActual, titleActual.contains(titleExpected));
    }



    public void setPassengerInformations(){
        sendKeyz(emailTxt, userProp.getProperty("EMAIL"));
        sendKeyz(phoneTxt, userProp.getProperty("phoneNumber"));
        String[] nameSurname = userProp.getProperty("adultNameSurname").split(",");
        String[] tcNoList = userProp.getProperty("adultTc").split(",");
        for (int i = 0; i< seatNumbersList.size(); i++){
            WebElement fullName = getDriver().findElement(By.id("name-"+seatNumbersList.get(i)+""));
            WebElement tc = getDriver().findElement(By.id("gov-id-"+seatNumbersList.get(i)+""));
            sendKeyz(fullName,nameSurname[i]);
            sendKeyz(tc,tcNoList[i]);
        }
    }
    public void closePopUpAndSetPnrCode() {
        logger.info("Close pop-up and set pnr code");
        waitUntilLoadingIsDone();
        try {
            waitUntilElementVisible(popUpCloseBtn);
            if (popUpCloseBtn.isDisplayed()) waitUntilElementVisibleAndClick(popUpCloseBtn);
        } catch (Exception e) {

        }
        ContextMap.addContext(ContextKeys.PNRCODE, pnrCodeTxt.getText());
        ContextMap.addContext(ContextKeys.BUSPARTNER, partnerName.getText());
        logger.info("PNR CODE : "+pnrCodeTxt.getText()+" Partner : " + partnerName.getText());

    }

}
