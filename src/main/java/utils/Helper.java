package utils;

import driver.DriverInit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class Helper extends DriverInit {
    public Helper() {
        PageFactory.initElements(getDriver(), this);
    }

    public static Properties testProp = ReadProperties.readProp("test.properties");
    public static Properties userProp = ReadProperties.readProp("user.properties");

    public void alertException() {
        ((JavascriptExecutor) getDriver()).executeScript("window.onbeforeunload = function(e){};");
    }

    //
    public void sendKeyz(WebElement element, String keys) {
        waitUntilElementVisible(element);
        element.sendKeys(keys);
    }

    public String getText(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
        return webElement.getText();
    }

    public String getTitle() {
        return getDriver().getTitle();

    }

    public void select(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }

    public void selectByText(WebElement webElement, String text) {
        Select select = new Select(webElement);
        try {
            select.selectByVisibleText(text);
        } catch (Exception e) {
            logger.info("Can't find select text:" + text + "...." + e);
        }
    }

    public void waitUntilElementVisibleAndClick(WebElement webElement) {
        Actions actions = new Actions(getDriver());
        wait.until(ExpectedConditions.visibilityOf(webElement)).click();
        try {
            if (webElement.isDisplayed() != true) {
                actions.moveToElement(webElement).build().perform();
            }
        } catch (Exception e) {

        }
    }

    public void waitUntilLoadingIsDone() {
        WebDriverWait waitLoading = new WebDriverWait(getDriver(), Duration.ofSeconds(60));
        waitLoading.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("[id='loading']")));
    }

    public void waitUntilLoadingIsShow(){
        WebDriverWait waitLoading = new WebDriverWait(getDriver(), Duration.ofSeconds(60));
        waitLoading.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.cssSelector("[id='loading']"))));
    }


    public void waitUntilListIsLoad(List<WebElement> element) {
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }

    public void waitUntilElementVisible(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitUntilElementClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitFor(int... timeOut) {
        int timeOutL = 2;
        if (timeOut.length != 0) {
            timeOutL = timeOut[0];
        }
        try {
            Thread.sleep(timeOutL * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public String getRandomString(Integer lenghtParam) {
        String SALTCHARS = "abcdefghijklmnoprstuvyz";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < lenghtParam) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    public int getRandomNumber(Integer bound) {
        Random rnd = new Random();
        if (bound <= 0) {
            bound = 1;
        }
        return (rnd.nextInt(bound));
    }

    public WebElement swipeUntilElement(WebElement webElement) {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(webElement).build().perform();
        return webElement;
    }

    public void clickMouseAction(WebElement webElement) {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(webElement).click().build().perform();
    }

    public static String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 2);
        return formatter.format(calendar.getTime());
    }

    public static String selectCustomDate(String curDate) throws ParseException {
        final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        final Date date = format.parse(curDate);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return format.format(calendar.getTime());
    }

}
