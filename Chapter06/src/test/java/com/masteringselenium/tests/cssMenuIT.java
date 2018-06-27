package com.masteringselenium.tests;

import com.masteringselenium.DriverBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class cssMenuIT extends DriverBase {

    private RemoteWebDriver driver;

    @BeforeTest
    public void bindDriver() throws MalformedURLException {
        driver = getDriver();
    }

    @Test
    public void automateCSSMenu() {
        driver.get("http://web.masteringselenium.com/cssMenu.html");
        Actions advancedActions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 5, 100);

        WebElement servicesMenuOption = driver.findElement(By.id("services"));
        WebElement webDevelopmentSubMenuOption = driver.findElement(By.cssSelector("#services > ul > li:nth-child(2)"));

        advancedActions.moveToElement(servicesMenuOption)
                .perform();

        wait.until(ExpectedConditions.visibilityOf(webDevelopmentSubMenuOption));

        advancedActions.moveToElement(webDevelopmentSubMenuOption)
                .click()
                .perform();
    }
}
