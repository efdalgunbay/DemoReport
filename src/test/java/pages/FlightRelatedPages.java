package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Helper;

public class FlightRelatedPages extends Helper {
    @FindBy(id="pax-toggle")
    WebElement passengersTypeSelection;
    @FindBy(css = "[class='pax-template']")
    WebElement passengerTypeTemplate;


    int flightPassegerCount;

    public void selectPassengerTypeAndCount(int count,String type){
        logger.info("Selecting the passenger type and count");
        waitUntilElementVisibleAndClick(passengersTypeSelection);
        waitUntilElementVisible(passengerTypeTemplate);
        WebElement selectedTypePlusBtn = getDriver().findElement(By.cssSelector("[data-type='"+type+"']>[class*='plus']"));
        int i = 1;
        while (i <=count) {
            waitUntilElementVisibleAndClick(selectedTypePlusBtn);
            i++;
        }
        waitUntilElementVisibleAndClick(passengersTypeSelection);
        flightPassegerCount = Integer.parseInt(passengersTypeSelection.getText().split(" ")[0]);

    }

}
