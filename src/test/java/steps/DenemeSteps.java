package steps;

import pages.DenemePage;

import io.cucumber.java.en.Given;
import utils.Helper;

public class DenemeSteps extends Helper {

    DenemePage denemePage=new DenemePage();


    @Given("Email Button Click")
    public void email_button() {
        denemePage.emailButton.click();
    }

    @Given("i go home")
    public void goHome() {
       denemePage.goToHome();


    }

    @Given("Email Button Clicks")
    public void email_button_click() {
        denemePage.emailButton2.click();

    }
}
