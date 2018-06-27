package com.masteringselenium.config;

import com.applitools.eyes.selenium.Eyes;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static com.masteringselenium.config.DriverType.FIREFOX;
import static com.masteringselenium.config.DriverType.valueOf;

public class DriverFactory {

    private RemoteWebDriver webDriver;
    private DriverType selectedDriverType;
    private String currentTestName;
    private Eyes eyes;

    private final String operatingSystem = System.getProperty("os.name").toUpperCase();
    private final String systemArchitecture = System.getProperty("os.arch");
    private final boolean useRemoteWebDriver = Boolean.getBoolean("remoteDriver");
    private final String eyesAPIKey = System.getProperty("eyesAPIKey", "<DEFAULT_KEY_HERE>");
    private final Boolean disableEyes = Boolean.getBoolean("disableEyes");

    public void setTestName(String testname) {
        currentTestName = testname;
    }

    public DriverFactory() {
        DriverType driverType = FIREFOX;
        String browser = System.getProperty("browser", driverType.toString()).toUpperCase();
        try {
            driverType = valueOf(browser);
        } catch (IllegalArgumentException ignored) {
            System.err.println("Unknown driver specified, defaulting to '" + driverType + "'...");
        } catch (NullPointerException ignored) {
            System.err.println("No driver specified, defaulting to '" + driverType + "'...");
        }
        selectedDriverType = driverType;
    }

    public RemoteWebDriver getDriver() throws MalformedURLException {
        if (null == webDriver) {
            instantiateWebDriver(selectedDriverType);
        }

        return webDriver;
    }

    public void quitDriver() {
        if (null != webDriver) {
            webDriver.quit();
            webDriver = null;
        }
    }

    public Eyes openEyes() throws Exception {
        if (null == eyes) {
            eyes = new Eyes();
            eyes.setApiKey(eyesAPIKey);
            eyes.setIsDisabled(disableEyes);
            eyes.open(getDriver(), "Google Example", currentTestName);
        }

        return eyes;
    }

    public void closeEyes() {
        try {
            eyes.close();
        } finally {
            eyes.abortIfNotClosed();
        }
        eyes = null;
    }

    private void instantiateWebDriver(DriverType driverType) throws MalformedURLException {
        System.out.println(" ");
        System.out.println("Local Operating System: " + operatingSystem);
        System.out.println("Local Architecture: " + systemArchitecture);
        System.out.println("Selected Browser: " + selectedDriverType);
        System.out.println("Connecting to Selenium Grid: " + useRemoteWebDriver);
        System.out.println(" ");

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        if (useRemoteWebDriver) {
            URL seleniumGridURL = new URL(System.getProperty("gridURL"));
            String desiredBrowserVersion = System.getProperty("desiredBrowserVersion");
            String desiredPlatform = System.getProperty("desiredPlatform");

            if (null != desiredPlatform && !desiredPlatform.isEmpty()) {
                desiredCapabilities.setPlatform(Platform.valueOf(desiredPlatform.toUpperCase()));
            }

            if (null != desiredBrowserVersion && !desiredBrowserVersion.isEmpty()) {
                desiredCapabilities.setVersion(desiredBrowserVersion);
            }

            desiredCapabilities.setBrowserName(selectedDriverType.toString());
            webDriver = new RemoteWebDriver(seleniumGridURL, desiredCapabilities);
        } else {
            webDriver = driverType.getWebDriverObject(desiredCapabilities);
        }
    }
}