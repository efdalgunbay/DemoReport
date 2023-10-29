package steps;

import io.cucumber.java.en.When;
import pages.HotelRelatedPages;

public class HotelSteps  {
    HotelRelatedPages hotelRelatedPages = new HotelRelatedPages();
    @When("search {string} with location")
    public void typeToOtelSearchBar(String location) {
        hotelRelatedPages.hotelSearch(location);
    }

}
