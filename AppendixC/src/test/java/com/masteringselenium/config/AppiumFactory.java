package com.masteringselenium.config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.Activity;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import static com.masteringselenium.config.AppiumDriverType.ANDROID;

public class AppiumFactory {

    private AppiumDriver driver;
    private AppiumDriverType selectedDriverConfiguration;
    private Activity currentActivity;

    private static final boolean USE_SELENIUM_GRID = Boolean.getBoolean("useSeleniumGrid");
    private static final String DEFAULT_SERVER_LOCATION = "http://127.0.0.1:4723/wd/hub";
    private static String APPIUM_SERVER_LOCATION = System.getProperty("appiumServerLocation", DEFAULT_SERVER_LOCATION);

    public AppiumFactory() {
        AppiumDriverType driverType = ANDROID;
        String appiumConfig = System.getProperty("appiumConfig", driverType.toString()).toUpperCase();
        if (null == APPIUM_SERVER_LOCATION || APPIUM_SERVER_LOCATION.trim().isEmpty()) {
            APPIUM_SERVER_LOCATION = DEFAULT_SERVER_LOCATION;
        }
        try {
            driverType = AppiumDriverType.valueOf(appiumConfig);
        } catch (IllegalArgumentException ignored) {
            System.err.println("Unknown driver specified, defaulting to '" + driverType + "'...");
        } catch (NullPointerException ignored) {
            System.err.println("No driver specified, defaulting to '" + driverType + "'...");
        }
        selectedDriverConfiguration = driverType;
    }

    public AppiumDriver getDriver() throws Exception {
        return getDriver(currentActivity);
    }

    public AppiumDriver getDriver(Activity desiredActivity) throws Exception {
        if (null != currentActivity && !currentActivity.equals(desiredActivity)) {
            quitDriver();
        }
        if (null == driver) {
            currentActivity = desiredActivity;
            instantiateWebDriver(selectedDriverConfiguration);
        }

        return driver;
    }

    public void quitDriver() {
        if (null != driver) {
            driver.quit();
            driver = null;
            currentActivity = null;
        }
    }

    private void instantiateWebDriver(AppiumDriverType appiumDriverType) throws MalformedURLException {
        System.out.println("Current Appium Config Selection: " + selectedDriverConfiguration);
        System.out.println("Current Appium Server Location: " + APPIUM_SERVER_LOCATION);
        System.out.println("Connecting to Selenium Grid: " + USE_SELENIUM_GRID);

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        if (Boolean.getBoolean("enableDebugMode")) {
            desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "3600");
        }
        Optional.ofNullable(System.getProperty("device_id", null))
                .ifPresent(deviceID -> desiredCapabilities.setCapability(MobileCapabilityType.UDID, deviceID));
        if (USE_SELENIUM_GRID) {
            URL seleniumGridURL = new URL(System.getProperty("gridURL"));
            String desiredVersion = System.getProperty("desiredVersion");
            String desiredPlatform = System.getProperty("desiredPlatform");

            if (null != desiredPlatform && !desiredPlatform.isEmpty()) {
                desiredCapabilities.setPlatform(Platform.valueOf(desiredPlatform.toUpperCase()));
            }

            if (null != desiredVersion && !desiredVersion.isEmpty()) {
                desiredCapabilities.setVersion(desiredVersion);
            }

            desiredCapabilities.setBrowserName(selectedDriverConfiguration.toString());
            driver = new AppiumDriver(seleniumGridURL, desiredCapabilities);
        } else {
            driver = appiumDriverType.createAppiumObject(new URL(APPIUM_SERVER_LOCATION), desiredCapabilities)
                    .setActivity(currentActivity)
                    .getAppiumDriver();
        }
    }
}
