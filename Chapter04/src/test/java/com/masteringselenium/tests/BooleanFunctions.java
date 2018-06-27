package com.masteringselenium.tests;

import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.internal.Locatable;

import java.util.Map;

public class BooleanFunctions {

    public static Function<WebDriver, Boolean> listenerIsRegisteredOnElement(final String listenerType, final WebElement element) {
        return new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                Map<String, Object> registeredListeners = (Map<String, Object>) ((JavascriptExecutor) driver).executeScript("return (window.jQuery != null) && (jQuery._data(jQuery(arguments[0]).get(0)), 'events')", element);
                for (Map.Entry<String, Object> listener : registeredListeners.entrySet()) {
                    if (listener.getKey().equals(listenerType)) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    public static Function<WebDriver, Boolean> elementHasStoppedMoving(final WebElement element) {
        return new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                Point initialLocation = ((Locatable) element).getCoordinates().inViewPort();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ignored) {
                    //ignored
                }
                Point finalLocation = ((Locatable) element).getCoordinates().inViewPort();
                return initialLocation.equals(finalLocation);
            }
        };
    }

    Function<WebDriver, Boolean> didWeFindElementFool = new Function<WebDriver, Boolean>() {
        public Boolean apply(WebDriver driver) {
            //Do something here
            return false;
        }
    };

    Function<WebDriver, Boolean> didWeFindElementFoor = new Function<WebDriver, Boolean>() {
        public Boolean apply(WebDriver driver) {
            return driver.findElements(By.id("foo")).size() > 0;
        }
    };

Function<WebDriver, Boolean> didWeFindElementFoo =
        driver -> driver.findElements(By.id("foo")).size() > 0;
}