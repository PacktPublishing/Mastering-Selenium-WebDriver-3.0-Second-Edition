package com.masteringselenium.config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public enum AppiumDriverType implements DriverSetup {


    ANDROID {
        public AppiumDriverType createAppiumObject(URL appiumServerLocation, DesiredCapabilities desiredCapabilities) {
            capabilities = desiredCapabilities;
            serverLocation = appiumServerLocation;
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.APPIUM);
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");

            if (ENABLE_DEBUG_MODE) {
                capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "3600");
            }

            return this;
        }

        public AppiumDriver getAppiumDriver() {
            return new AndroidDriver(serverLocation, capabilities);
        }

    };

    private static final boolean ENABLE_DEBUG_MODE = Boolean.getBoolean("enableDebugMode");
    DesiredCapabilities capabilities;
    URL serverLocation;

    public AppiumDriverType setActivity(Activity activity) {
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, activity.getAppPackage());
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, activity.getAppActivity());

        return this;
    }
}