package com.masteringselenium.tests;

import com.masteringselenium.DriverBase;
import com.masteringselenium.page_objects.GoogleHomePage;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicTestIT extends DriverBase {

    private RemoteWebDriver driver;

    @BeforeMethod
    public void setup() throws MalformedURLException {
        driver = getDriver();
    }

    @Test
    public void googleCheeseExample() {
        driver.get("http://www.google.com");

        GoogleHomePage googleHomePage = new GoogleHomePage();

        System.out.println("Page title is: " + driver.getTitle());

        googleHomePage.enterSearchTerm("Cheese")
                .submitSearch();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until((ExpectedCondition<Boolean>) d -> d.getTitle().toLowerCase().startsWith("cheese"));

        System.out.println("Page title is: " + driver.getTitle());
        assertThat(driver.getTitle()).containsIgnoringCase("cheese");
    }
}