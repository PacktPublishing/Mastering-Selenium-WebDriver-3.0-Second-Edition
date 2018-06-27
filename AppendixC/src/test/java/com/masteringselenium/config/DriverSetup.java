package com.masteringselenium.config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.Activity;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public interface DriverSetup {
    DriverSetup createAppiumObject(URL appiumServerLocation, DesiredCapabilities capabilities);

    DriverSetup setActivity(Activity activity);

    AppiumDriver getAppiumDriver();
}