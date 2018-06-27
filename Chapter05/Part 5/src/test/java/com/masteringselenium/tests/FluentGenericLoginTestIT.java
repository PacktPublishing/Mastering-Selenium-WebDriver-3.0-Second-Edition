package com.masteringselenium.tests;

import com.masteringselenium.DriverBase;
import com.masteringselenium.fluent_page_objects.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class
FluentGenericLoginTestIT extends DriverBase {

    private WebDriver driver;

    @BeforeMethod
    public void setup() throws MalformedURLException {
        driver = getDriver();
    }

@Test
public void logInToTheWebsite() {
    driver.get("http://web.masteringselenium.com/index.html");
    loginPage = new LoginPage();

    loginPage.enterUsername("foo")
            .enterPassword("bar")
            .andLogin();

    assertThat(driver.getTitle()).isEqualTo("Logged in");
}

@Test
public void logInToTheWebsiteWithClientSideValidationCheck() {
    driver.get("http://web.masteringselenium.com/index.html");
    loginPage = new LoginPage();

    loginPage.enterUsername("foo")
            .enterPassword("bar");

    //TODO Perform client side validation check here

    loginPage.andLogin();

    assertThat(driver.getTitle()).isEqualTo("Logged in");
}

}
