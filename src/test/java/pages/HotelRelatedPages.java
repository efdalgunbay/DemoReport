package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Helper;
import utils.context.ContextKeys;
import utils.context.ContextMap;

public class HotelRelatedPages extends Helper {
    @FindBy(id = "origin-input")
    WebElement hotelSearchTxt;
    @FindBy(id = "departure-input")
    WebElement hotelCheckInInput;
    @FindBy(id="hotel-filter-container")
    WebElement hotelFilterContainer;
    @FindBy(css="[class='list-container']")
    WebElement hotelListContainer;
    @FindBy(id="quick-filters-bar")
    WebElement hotelQuickFilters;


    public void hotelSearch(String location){
        logger.info("Searching a hotel location");
        getDriver().navigate().refresh();
        ContextMap.addContext(ContextKeys.HOTELLOCATION,location);
        waitUntilElementVisibleAndClick(hotelSearchTxt);
        sendKeyz(hotelSearchTxt,location);
        waitFor(4);
        WebElement listedResults = getDriver().findElement(By.xpath("(//*[@class='origin-name'][contains(text(),'"+location+"')])[1]"));
        clickMouseAction(listedResults);
    }


    public void checkListPageIsOpened(){
        logger.info("Checking hotel list pages elements");
        waitUntilLoadingIsDone();
        Assert.assertTrue("Hotel filter container has not displayed",hotelFilterContainer.isDisplayed());
        Assert.assertTrue("Hotel list container has not displayed",hotelListContainer.isDisplayed());
        Assert.assertTrue("Quick filters has not displayed ",hotelQuickFilters.isDisplayed());

    }

}
