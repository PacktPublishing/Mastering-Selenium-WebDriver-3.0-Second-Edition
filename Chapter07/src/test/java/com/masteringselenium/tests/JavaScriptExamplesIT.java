package com.masteringselenium.tests;

import com.masteringselenium.DriverBase;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class JavaScriptExamplesIT extends DriverBase {

    private RemoteWebDriver driver;

    @BeforeMethod
    private void setup() throws MalformedURLException {
        driver = DriverBase.getDriver();
    }

    @Test
    private void javascriptExample1() {
        FirefoxDriver driver = new FirefoxDriver(new FirefoxOptions());
        driver.executeScript("console.log('I logged something to the Javascript console');");
    }

    @Test
    private void javascriptExample2() {
        FirefoxDriver driver = new FirefoxDriver(new FirefoxOptions());
        Object response = driver.executeScript("return console.log('I logged something to the Javascript console');");

    }

    @Test
    private void javascriptExample3() {
        FirefoxDriver driver = new FirefoxDriver(new FirefoxOptions());
        String animal = "Lion";
        int seen = 5;

        driver.executeScript("console.log('I have seen a ' + arguments[0] + ' ' + arguments[1] + ' times(s)');", animal, seen);
    }

    @Test
    private void javascriptExample4() {
        driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
        driver.executeAsyncScript("var callback = arguments[arguments.length - 1]; window.setTimeout(callback, 25000);");
        driver.get("http://www.google.com");
    }

    @Test
    private void javascriptExample5() {
        driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
        driver.executeAsyncScript("var callback = arguments[arguments.length - 1]; window.setTimeout(callback, 25000);");
        driver.get("http://www.google.com");
    }

    @Test
    private void javascriptExample6() {
        driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
        driver.executeScript("var callback = arguments[arguments.length - 1]; window.setTimeout(callback, 25000);");
        driver.get("http://www.google.com");
    }
}