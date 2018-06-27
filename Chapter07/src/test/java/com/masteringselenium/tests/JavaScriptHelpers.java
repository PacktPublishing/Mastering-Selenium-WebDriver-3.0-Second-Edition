package com.masteringselenium.tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

public class JavaScriptHelpers {

    private String getHiddenText(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) ((RemoteWebElement) element).getWrappedDriver();
        return (String) js.executeScript("return arguments[0].text", element);
    }

}
