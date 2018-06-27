package com.masteringselenium.tests;

import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Functions {

    Function<WebDriver, WebElement> weFindElementFooI =
            new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {
                    //Do something here
                    return null;
                }
            };

    static Function<WebDriver, WebElement> weFindElementFoo =
            new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {
                    return driver.findElement(By.id("foo"));
                }
            };

}