package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.*;


public class CommonSteps {
    FlightRelatedPages flightRelatedPages = new FlightRelatedPages();
    HotelRelatedPages hotelRelatedPages = new HotelRelatedPages();
    CommonPages commonPages = new CommonPages();
    BusRelatedPages busRelatedPages = new BusRelatedPages();
    SeaRelatedPages seaRelatedPages = new SeaRelatedPages();


    @And("insert ticket informations and search ticket")
    public void insertTicketInformationsAndSearchTicket() {
        commonPages.insertTicketInfos();
        commonPages.clickInquireTicket();
    }

    @Given("go to homepage")
    public void goToHomepage() {
        commonPages.goToHome();
    }

    @And("click pay button")
    public void clickPayButton() {
        seaRelatedPages.clickPayButton();
    }

    @And("^open to (Bus|Flight|Hotel|RentCar|Sea) main page$")
    public void openToMainPage(String page) {
        commonPages.selectVertical(page);
    }


    @When("select where from as {string}")
    public void selectWhereFromAs(String route) {
        commonPages.selectWhereFrom(route);
    }

    @And("select where to as {string}")
    public void selectWhereToAs(String route) {
        commonPages.selectWhereTo(route);
    }

    @And("select departure date")
    public void selectDepartureDate() {
        commonPages.setDepartureDate();
    }

    @And("click search button")
    public void clickSearchButton() {
        commonPages.clickSearchTicket();
    }

    @And("go to trips page")
    public void goToTripsPage() {
        commonPages.clickTripsBtn();
    }

    @And("^change the language of site to (tr|en|ro|es|bg|ru|mx|sr|fr|de|pl|pt|br|ua)$")
    public void changeTheLanguageOfSiteTo(String language) {
        commonPages.selectLanguage(language);
    }

    @And("^click on the (open|change|cancel) button in the ticket search ?")
    public void clickOnTheTicketActionButton(String btn) {
        commonPages.ticketProcess(btn);
    }

    @And("click on cancel button in the pop-up")
    public void clickOnCancelButtonInThePopUp() {
        commonPages.clickCancelTicketOnPopUp();
    }

    @Then("check succes pop-up is displayed")
    public void checkSuccesPopUpIsDisplayed() {
        commonPages.checkSuccessPopUp();
    }

    @And("click continue button on list pages")
    public void clickContinueButton() {
        busRelatedPages.addContextSelectedSeatsList();
        seaRelatedPages.clickConfirmAndContinue();
    }

    @And("^enter card informations (dummy|real)?")
    public void enterCardInformations(String cardType) {
        seaRelatedPages.setCardNumber(cardType);
    }


    @And("^add (1|2|3) passenger (adult|children|student|elderly)?")
    public void addPassenger(int count, String type) {
        flightRelatedPages.selectPassengerTypeAndCount(count,type);
    }

    @Then("check list page opened")
    public void checkListPageIsOpen() {
        hotelRelatedPages.checkListPageIsOpened();
    }
}
