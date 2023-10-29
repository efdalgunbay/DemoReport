package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Helper;

public class DenemePage extends Helper {
    @FindBy(id = "email")
    WebElement emailTxt;


}
