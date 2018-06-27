package com.masteringselenium.tests;

import com.masteringselenium.DriverBase;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.masteringselenium.tests.Functions.weFindElementFoo;

public class WaitExamplesIT extends DriverBase {

    private static final int DEFAULT_TIMEOUT_IN_SECONDS = 10;

    public WebElement reliableFindElement(final WebDriver driver, final By selector) {
        WebElement element;
        long endTime = System.currentTimeMillis() + Duration.ofSeconds(DEFAULT_TIMEOUT_IN_SECONDS).toMillis();
        while (System.currentTimeMillis() < endTime) {
            try {
                element = driver.findElement(selector);
                return element;
            } catch (NoSuchElementException ignored) {
                System.out.println("Not found, trying again...");
            }
        }
        throw new NoSuchElementException("Could not find " + selector);
    }


    @Test
    public void loadJquerySite() throws MalformedURLException {
        WebDriver driver = getDriver();

        driver.get("http://jquery.com");

        WebDriverWait wait = new WebDriverWait(driver, 15, 100);
        wait.until(AdditionalConditions.jQueryAJAXCallsHaveCompleted());


    }

    @Test
    public void loadAngularJSSite() throws MalformedURLException {
        WebDriver driver = getDriver();

        driver.get("https://angularjs.org");

        WebDriverWait wait = new WebDriverWait(driver, 15, 100);
        wait.until(AdditionalConditions.angularHasFinishedProcessing());
    }

    @Test
    public void fluentWaitIgnoringSingleException() throws MalformedURLException {
        WebDriver driver = getDriver();

        driver.get("https://angularjs.org");

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .withMessage("The message you will see in if a TimeoutException is thrown");


        wait.until(AdditionalConditions.angularHasFinishedProcessing());
    }

    @Test
    public void fluentWaitIgnoringMultipleExceptions() throws MalformedURLException {
        WebDriver driver = getDriver();

        driver.get("https://angularjs.org");

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .withMessage("The message you will see in if a TimeoutException is thrown");


        wait.until(AdditionalConditions.angularHasFinishedProcessing());
    }

    @Test
    public void fluentWaitIgnoringAListOfExceptions() throws MalformedURLException {
        WebDriver driver = getDriver();

        driver.get("https://angularjs.org");

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(500))
                .ignoreAll(Arrays.asList(
                        NoSuchElementException.class,
                        StaleElementReferenceException.class
                ))
                .withMessage("The message you will see in if a TimeoutException is thrown");


        wait.until(AdditionalConditions.angularHasFinishedProcessing());

        wait.until(weFindElementFoo);

    }

    @Test
    public void fluentWaitIgnoringACollectionOfExceptions() throws MalformedURLException {
        WebDriver driver = getDriver();

        driver.get("https://angularjs.org");
        List<Class<? extends Throwable>> exceptionsToIgnore = new ArrayList<Class<? extends Throwable>>() {
            {
                add(NoSuchElementException.class);
                add(StaleElementReferenceException.class);
            }
        };

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(500))
                .ignoreAll(exceptionsToIgnore)
                .withMessage("The message you will see in if a TimeoutException is thrown");


        wait.until(AdditionalConditions.angularHasFinishedProcessing());
    }
}