package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Helper;
import utils.context.ContextKeys;
import utils.context.ContextMap;

import java.util.List;

public class CommonPages extends Helper {
    @FindBy(id = "origin")
    WebElement selectWhereFrom;
    @FindBy(id = "origin-input")
    WebElement selectWhereFromTxt;
    @FindBy(id = "destination")
    WebElement selectWhereTo;
    @FindBy(id = "destination-input")
    WebElement selectWhereToText;
    @FindBy(id = "departure-input")
    WebElement departureDate;
    @FindBy(id = "return-input-placeholder")
    WebElement returnDate;
    @FindBy(id = "search-button")
    WebElement searchBtn;
    @FindBy(css = "[id='origin']>[class='results']>ul>[class*='item']")
    List<WebElement> resultsWhereFrom;
    @FindBy(css = "[id='destination']>[class='results']>ul>[class*='item']")
    List<WebElement> resultsWhereTo;
    @FindBy(css = "[class='results']>ul>[class='item']")
    List<WebElement> resultsWhereFromeListPage;
    @FindBy(id = "accept")
    WebElement cookieAcceptBtn;
    @FindBy(css = "[href*='/tickets']")
    WebElement tripsWithoutLogin;
    @FindBy(css = "[class='language']")
    WebElement languageBox;
    @FindBy(css = "[class*='open-ticket-action']")
    WebElement changeToOpenTicketBtn;
    @FindBy(css = "[class*='change-action']")
    WebElement changeBtn;
    @FindBy(css = "[class*='cancel-action']")
    WebElement cancelBtn;
    @FindBy(css = "[data-action='cancel-ticket']")
    WebElement popUpCancelTicketbtn;
    @FindBy(css = "[data-action='open-ticket']")
    WebElement popUpOpenTicketbtn;
    @FindBy(css = "[data-action='close']")
    WebElement closePopUp;
    @FindBy(css = "[class='load']>[class='loading']")
    WebElement popUpLoadingImg;
    @FindBy(css = "[class='success']")
    WebElement succesPopUp;
    @FindBy(id = "partner")
    WebElement busPartnerGroup;
    @FindBy(id = "pnr")
    WebElement pnr;
    @FindBy(id = "contact")
    WebElement contactInfo;
    @FindBy(id = "send-button")
    WebElement inquireTicketBtn;
    @FindBy(css = "[class='item highlighted']")
    WebElement resultHighlighted;
    @FindBy(css = "[class='results']")
    WebElement resultsList;


    public void insertTicketInfos() {
        logger.info("Getting a ticket with phone number");
        waitUntilElementVisibleAndClick(busPartnerGroup);
        sendKeyz(busPartnerGroup, ContextMap.getContextValue(ContextKeys.BUSPARTNER));
        waitUntilElementVisible(resultsList);
        waitUntilElementVisibleAndClick(resultHighlighted);
        sendKeyz(pnr, ContextMap.getContextValue(ContextKeys.PNRCODE));
        sendKeyz(contactInfo, userProp.getProperty("phoneNumber"));
    }

    public void clickInquireTicket() {
        logger.info("Clicking inquire ticket button");
        waitUntilElementVisibleAndClick(inquireTicketBtn);
    }


    public void ticketProcess(String btn) {
        switch (btn) {
            case "open":
                logger.info("Ticket is changing to open ticket -  PNR :  " + ContextMap.getContextValue(ContextKeys.PNRCODE) + "");
                waitUntilLoadingIsDone();
                waitUntilElementVisibleAndClick(changeToOpenTicketBtn);
                break;

            case "change":
                logger.info("Ticket is changing - PNR :  " + ContextMap.getContextValue(ContextKeys.PNRCODE) + "");
                waitUntilLoadingIsDone();
                waitUntilElementVisibleAndClick(changeBtn);
                break;

            case "cancel":
                logger.info("Ticket is cancelling - PNR :  " + ContextMap.getContextValue(ContextKeys.PNRCODE) + "");
                waitUntilLoadingIsDone();
                waitUntilElementClickable(cancelBtn);
                waitUntilElementVisibleAndClick(cancelBtn);
                break;

        }

    }

    public void clickCancelTicketOnPopUp() {
        logger.info("Clicking on the cancel button");
        waitUntilElementVisibleAndClick(popUpCancelTicketbtn);
    }

    public void checkSuccessPopUp() {
        logger.info("Checking success pop-up is displayed");
        waitUntilLoadingIsDone();
        wait.until(ExpectedConditions.invisibilityOf(popUpLoadingImg));
        Assert.assertTrue(succesPopUp.isDisplayed());
    }


    public void selectLanguage(String language) {
        logger.info("Selecting language");
        waitUntilElementVisibleAndClick(languageBox);
        waitFor(1);
        WebElement lang = getDriver().findElement(By.cssSelector("[class='content']>[href='/" + language + "']"));
        waitUntilElementVisibleAndClick(lang);
    }


    public void selectWhereFrom(String route) {
        logger.info("click selectWhereFrom : " + selectWhereFrom);
        try {
            waitUntilElementVisibleAndClick(selectWhereFrom);
            selectWhereFromTxt.sendKeys(route);
            waitFor(4);
            ContextMap.addContext(ContextKeys.WHEREFROM, resultsWhereFrom.get(0).getText());
            waitUntilElementVisibleAndClick(resultsWhereFrom.get(0));
        } catch (Exception e) {
            logger.info("SEFER BULUNAMADI.... : " + e);
        }
    }


    public void setSelectWhereFromListPage() {
        waitUntilElementVisibleAndClick(selectWhereFromTxt);
        selectWhereFromTxt.sendKeys(getRandomString(1));
        waitFor(4);
        ContextMap.addContext(ContextKeys.WHEREFROM, resultsWhereFromeListPage.get(0).getText());
        waitUntilElementVisibleAndClick(resultsWhereFromeListPage.get(0));

    }

    public void selectWhereTo(String route) {
        logger.info("click selectWhereTo   : " + selectWhereTo);
        waitUntilElementVisibleAndClick(selectWhereTo);
        selectWhereToText.sendKeys(route);
        waitFor(2);
        ContextMap.addContext(ContextKeys.WHERETO, resultsWhereTo.get(0).getText());
        waitUntilElementVisibleAndClick(resultsWhereTo.get(0));

    }

    private WebElement getPlaceInResults(String route) {
        WebElement place = null;
        for (int i = 0; i < resultsWhereTo.size(); i++) {
            if (resultsWhereTo.get(i).getText().contains(route)) {
                place = resultsWhereTo.get(i);
                break;
            }

        }
        return place;
    }

    public void setDepartureDate() {
        waitFor(2);
        waitUntilElementVisibleAndClick(departureDate);
        WebElement selectDate = getDriver().findElement(By.xpath("//*[@data-date='" + Helper.getDate() + "']"));
        waitUntilElementVisibleAndClick(selectDate);
    }


    public void clickUyeHrefs(String menu) {
        WebElement menus = getDriver().findElement(By.xpath("(//*[@href='/uye/" + menu + "'])[1]"));
        waitUntilElementVisibleAndClick(menus);
    }

    public void clickSearchTicket() {
        waitFor(2);
        clickMouseAction(searchBtn);
    }

    public void goToHome() {
        logger.info("ObiletQALog: going to homepage");
        String env = Helper.testProp.getProperty("environment");
        String baseURI = null;
        switch (env) {
            case "preprod":
                baseURI = "https://preprod.obilet.com/";
                break;
            case "stage":
                baseURI = "https://stage.obilet.com/";
                break;
            case "prod":
                baseURI = "https://www.obilet.com/";
                break;
        }
        ContextMap.addContext(ContextKeys.BASEURI, baseURI);
        getDriver().navigate().to(baseURI);
        try {
            if (cookieAcceptBtn.isDisplayed()) waitUntilElementVisibleAndClick(cookieAcceptBtn);
        } catch (Exception e) {

        }
        logger.info("Checking main page snapshot with percy");

    }

    public void selectVertical(String page) {
        logger.info("ObiletQALog: select vertical ");
        WebElement vertical = getDriver().findElement(By.cssSelector("[data-event-action='" + page + "']"));
        waitUntilElementVisibleAndClick(vertical);
    }


    public void getFooterPages(String page) {
        logger.info("Go to " + page + " footer page");
        getDriver().get(ContextMap.getContextValue(ContextKeys.BASEURI) + "/" + page);
    }

    public void clickTripsBtn() {
        logger.info("Clicking to trips button");
        swipeUntilElement(tripsWithoutLogin);
        waitUntilElementClickable(tripsWithoutLogin);
        waitUntilElementVisibleAndClick(tripsWithoutLogin);
    }
}
