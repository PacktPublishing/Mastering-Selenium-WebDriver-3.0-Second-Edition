package com.masteringselenium.tests;

import com.applitools.eyes.MatchLevel;
import com.masteringselenium.DriverBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class BasicIT extends DriverBase {

    private RemoteWebDriver driver;

    @BeforeMethod
    public void setup() throws MalformedURLException {
        driver = getDriver();
    }

    private void googleExampleThatSearchesFor(final String searchString) throws Exception {
        driver.get("http://www.google.com");

        WebElement searchField = driver.findElement(By.name("q"));

        searchField.clear();
        searchField.sendKeys(searchString);

        System.out.println("Page title is: " + driver.getTitle());

        searchField.submit();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until((ExpectedCondition<Boolean>) d -> d.getTitle().toLowerCase().startsWith(searchString.toLowerCase()));

        openEyes().setMatchLevel(MatchLevel.LAYOUT);
        openEyes().checkWindow("Search results for " + searchString);

        System.out.println("Page title is: " + driver.getTitle());
    }

    @Test
    public void googleCheeseExample() throws Exception {
        googleExampleThatSearchesFor("Cheese!");
    }

    @Test
    public void googleMilkExample() throws Exception {
        googleExampleThatSearchesFor("Milk!");
    }
}
