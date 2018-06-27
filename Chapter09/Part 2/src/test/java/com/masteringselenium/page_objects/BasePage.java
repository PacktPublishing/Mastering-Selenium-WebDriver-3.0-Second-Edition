package com.masteringselenium.page_objects;

import com.masteringselenium.DriverBase;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;

public abstract class BasePage {

    protected RemoteWebDriver driver;

    public BasePage() {
        try {
            driver = DriverBase.getDriver();
        } catch (MalformedURLException ignored) {
            //This will be be thrown when the test starts if it cannot connect to a RemoteWebDriver Instance
        }
    }
}