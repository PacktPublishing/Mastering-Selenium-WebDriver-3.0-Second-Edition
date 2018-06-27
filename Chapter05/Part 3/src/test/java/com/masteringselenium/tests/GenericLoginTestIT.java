package com.masteringselenium.tests;

import com.masteringselenium.DriverBase;
import com.masteringselenium.page_objects.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GenericLoginTestIT extends DriverBase {

    private WebDriver driver;

    @BeforeMethod
    public void setup() throws MalformedURLException {
        driver = getDriver();
    }

    @Test
    public void logInToTheWebsiteStep1() {
        driver.get("http://web.masteringselenium.com/index.html");

        WebElement username = driver.findElement(LoginPage.usernameLocator);
        WebElement password = driver.findElement(LoginPage.passwordLocator);
        WebElement submitButton = driver.findElement(LoginPage.loginButtonLocator);

        username.sendKeys("foo");
        password.sendKeys("bar");
        submitButton.click();

        assertThat(driver.getTitle()).isEqualTo("Logged in");
    }

    @Test
    public void logInToTheWebsiteStep2() {
        driver.get("http://web.masteringselenium.com/index.html");
        LoginPage.logInWithUsernameAndPassword("foo", "bar", driver);

        assertThat(driver.getTitle()).isEqualTo("Logged in");
    }
}