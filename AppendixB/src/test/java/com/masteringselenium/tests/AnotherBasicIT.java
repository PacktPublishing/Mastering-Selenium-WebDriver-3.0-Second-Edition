package com.masteringselenium.tests;

import com.masteringselenium.DriverBase;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;

public class AnotherBasicIT extends DriverBase {

    private RemoteWebDriver driver;

    @Before
    public void setup() throws MalformedURLException {
        driver = getDriver();
    }

    private void googleExampleThatSearchesFor(final String searchString) {
        driver.get("http://www.google.com");

        WebElement searchField = driver.findElement(By.name("q"));

        searchField.clear();
        searchField.sendKeys(searchString);

        System.out.println("Page title is: " + driver.getTitle());

        searchField.submit();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until((ExpectedCondition<Boolean>) d -> d.getTitle().toLowerCase().startsWith(searchString.toLowerCase()));

        System.out.println("Page title is: " + driver.getTitle());
    }

    @Test
    public void googleMangoExample() {
        googleExampleThatSearchesFor("Mango!");
    }

    @Test
    public void googleBananaExample() {
        googleExampleThatSearchesFor("Banana!");
    }
}
