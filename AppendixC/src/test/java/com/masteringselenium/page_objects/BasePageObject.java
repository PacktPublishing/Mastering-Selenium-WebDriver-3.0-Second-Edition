package com.masteringselenium.page_objects;

import com.masteringselenium.AppiumBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePageObject {

    AppiumDriver driver;
    WebDriverWait webDriverWait;
    TouchAction touchAction;

    BasePageObject() {
        try {
            this.driver = AppiumBase.getDriver();
        } catch (Exception ignored) {
            //This will be be thrown when the test starts if it cannot connect to a RemoteWebDriver Instance
        }
        this.webDriverWait = new WebDriverWait(driver, 30, 250);
        this.touchAction = new TouchAction(driver);
    }
}