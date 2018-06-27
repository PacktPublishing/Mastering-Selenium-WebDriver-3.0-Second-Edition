package com.masteringselenium.tests;

import com.masteringselenium.DriverBase;
import com.masteringselenium.accessKeys.Browser;
import com.masteringselenium.accessKeys.OperatingSystem;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.List;

import static com.masteringselenium.accessKeys.Browser.FIREFOX;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class AccessKeysIT extends DriverBase {

    private RemoteWebDriver driver;

    @BeforeTest
    public void bindDriver() throws MalformedURLException {
        driver = getDriver();
    }

    @Test
    public void testThatUsingAccessKeysWorks() {
        driver.get("http://web.masteringselenium.com/accessKeysHome.html");
        WebDriverWait wait = new WebDriverWait(driver, 5, 100);
        List<WebElement> home = driver.findElements(By.id("home"));

        assertThat(home.size()).isEqualTo(1);

        triggerAccessKey("9");
        WebElement access = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("about")));

        home = driver.findElements(By.id("home"));
        assertThat(home.size()).isEqualTo(0);
        assertThat(access.isDisplayed()).isTrue();
    }

    private void triggerAccessKeyOld(String accessKey) {
        Actions advancedActions = new Actions(driver);
        advancedActions.keyDown(Keys.CONTROL)
                .keyDown(Keys.ALT)
                .sendKeys(accessKey)
                .keyUp(Keys.CONTROL)
                .keyUp(Keys.ALT)
                .perform();
    }

    private void triggerAccessKeyLocal(String accessKey) {
        Actions advancedActions = new Actions(driver);
        OperatingSystem currentOS = OperatingSystem.getCurrentOperatingSystem();
        String currentBrowserName = driver.getCapabilities().getBrowserName();
        Browser currentBrowser = Browser.getBrowserType(currentBrowserName);

        switch (currentOS) {
            case OSX:
                advancedActions.keyDown(Keys.CONTROL)
                        .keyDown(Keys.ALT)
                        .sendKeys(accessKey)
                        .keyUp(Keys.ALT)
                        .keyUp(Keys.CONTROL)
                        .perform();
                break;
            case LINUX:
            case WINDOWS:
                if (currentBrowser.equals(FIREFOX)) {
                    advancedActions.keyDown(Keys.ALT)
                            .keyDown(Keys.SHIFT)
                            .sendKeys(accessKey)
                            .keyUp(Keys.SHIFT)
                            .keyUp(Keys.ALT)
                            .perform();
                } else {
                    advancedActions.keyDown(Keys.ALT)
                            .sendKeys(accessKey)
                            .keyUp(Keys.ALT)
                            .perform();
                }
                break;
            default:
                throw new IllegalArgumentException("Unrecognised operating system name '" + currentOS + "'");
        }
    }

    private void triggerAccessKey(String accessKey) {
        Actions advancedActions = new Actions(driver);
        Platform currentOS = driver.getCapabilities().getPlatform();
        Platform currentOSFamily = (null == currentOS.family() ? currentOS : currentOS.family());
        String currentBrowserName = driver.getCapabilities().getBrowserName();
        Browser currentBrowser = Browser.getBrowserType(currentBrowserName);

        switch (currentOSFamily) {
            case MAC:
                advancedActions.keyDown(Keys.CONTROL)
                        .keyDown(Keys.ALT)
                        .sendKeys(accessKey)
                        .keyUp(Keys.ALT)
                        .keyUp(Keys.CONTROL)
                        .perform();
                break;
            case UNIX:
            case WINDOWS:
                if (currentBrowser.equals(FIREFOX)) {
                    advancedActions.keyDown(Keys.ALT)
                            .keyDown(Keys.SHIFT)
                            .sendKeys(accessKey)
                            .keyUp(Keys.SHIFT)
                            .keyUp(Keys.ALT)
                            .perform();
                } else {
                    advancedActions.keyDown(Keys.ALT)
                            .sendKeys(accessKey)
                            .keyUp(Keys.ALT)
                            .perform();
                }
                break;
            default:
                throw new IllegalArgumentException("Unrecognised operating system name '" + currentOS + "'");
        }
    }

}
