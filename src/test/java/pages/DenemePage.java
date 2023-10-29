package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Helper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.context.ContextKeys;
import utils.context.ContextMap;

public class DenemePage extends Helper {

    @FindBy(xpath = "(//span[@class='MuiButton-label'])[1]")
    public WebElement emailButton;

    @FindBy(xpath = "(//span[@class='MuiButton-label'])[4]")
    public WebElement emailButton2;


    public void goToHome() {
        logger.info("Home Page Displayed");
        String env = Helper.testProp.getProperty("environment");
        String baseURI = null;
        switch (env) {
            case "preprod":
                baseURI = "https://www.google.com/";
                break;
            case "stage":
                baseURI = "https://www.google.com/";
                break;
            case "prod":
                baseURI = "https://www.google.com/";
                break;
        }
        ContextMap.addContext(ContextKeys.BASEURI, baseURI);
        getDriver().navigate().to(baseURI);
        try {

        } catch (Exception e) {

        }
        logger.info("Checking main page snapshot with percy");

    }


    }

