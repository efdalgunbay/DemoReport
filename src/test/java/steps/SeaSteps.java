package steps;

import io.cucumber.java.en.And;
import pages.SeaRelatedPages;

public class SeaSteps {
    SeaRelatedPages seaRelatedPages = new SeaRelatedPages();

    @And("^select random ferry trip and seat for (ido|budo)?")
    public void selectRandomTripAndSeatFor(String company) {
        seaRelatedPages.selectRandomTripAndSeat(company);
    }

    @And("enter passenger informations for sea")
    public void enterPassengerInformationsForSea() {
        seaRelatedPages.setPassengerInfoForSinglePassenger();
    }


}
