package driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Helper;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class DriverInit {
    protected static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    public static WebDriverWait wait;
    public static final Logger logger = LogManager.getLogger(DriverInit.class);


    public void setDriver() {
        String env = System.getProperty("component") != null ?
                System.getProperty("component") : Helper.testProp.getProperty("component");
        String browserName = System.getProperty("browser") != null ?
                System.getProperty("browser") : Helper.testProp.getProperty("browser");
        switch (env) {
            case "MobileWeb":
                if (browserName.equalsIgnoreCase("Chrome")) {
                    Map<String, String> mobileEmulation = new HashMap<>();
                    mobileEmulation.put("deviceName", "Nexus 5");
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
                    chromeOptions.addArguments("start-maximized");
                    chromeOptions.addArguments("enable-automation");
                    chromeOptions.addArguments("--disable-popup-blocking");
                    chromeOptions.addArguments("--disable-notifications");
                    if (browserName.contains("headless")) {
                        chromeOptions.addArguments("--headless=chrome");
                    }
                    webDriver.set((new ChromeDriver(chromeOptions)));
                }
                break;
            case "Web":
                if (browserName.contains("Chrome")) {
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--disable-notifications");
                    chromeOptions.addArguments("test-type");
                    chromeOptions.addArguments("disable-popup-blocking");
                    chromeOptions.addArguments("ignore-certificate-errors");
                    chromeOptions.addArguments("disable-translate");
                    chromeOptions.addArguments("--start-maximized");
                    chromeOptions.addArguments("disable-plugins");
                    if (browserName.contains("headless")) {
                        chromeOptions.addArguments("--headless=chrome");
                    }
                    webDriver.set((new ChromeDriver(chromeOptions)));
                } else if (browserName.equalsIgnoreCase("Mozilla")) {
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("start-maximized");
                    firefoxOptions.addArguments("enable-automation");
                    firefoxOptions.addArguments("--disable-popup-blocking");
                    firefoxOptions.addArguments("--disable-notifications");
                    if (browserName.contains("headless")) {
                        firefoxOptions.addArguments("headless");
                    }
                    webDriver.set((new FirefoxDriver(firefoxOptions)));
                }
                break;

        }

        if (env.equalsIgnoreCase("preprod") || env.equalsIgnoreCase("stage")) {
            wait = new WebDriverWait(getDriver(), Duration.of(60, ChronoUnit.SECONDS));
        } else {
            wait = new WebDriverWait(getDriver(), Duration.of(45, ChronoUnit.SECONDS));
        }

    }

    public static WebDriver getDriver() {
        return webDriver.get();
    }

    public static void teardown() {
        WebDriver driver = webDriver.get();
        if (driver != null) {
            driver.quit();
            webDriver.remove();
        }
    }


}
