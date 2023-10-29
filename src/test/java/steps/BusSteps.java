package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import pages.BusRelatedPages;

public class BusSteps {
    BusRelatedPages busRelatedPages = new BusRelatedPages();

    @And("enter passenger informations for bus")
    public void enterPassengerInformationsForBus() {
        busRelatedPages.setPassengerInformations();
    }

    @And("select random bus trip")
    public void selectRandomBusTrip() {
        busRelatedPages.selectRandomTripBus();
    }

    @And("set pnr code and partner name")
    public void closePopupAndGetPnrCode() {
        busRelatedPages.closePopUpAndSetPnrCode();
    }

    @And("^select random seat for (male|female)?")
    public void selectRandomSeatFor(String gender) {
        busRelatedPages.busSelectAvailableSeat(gender);
    }



}
