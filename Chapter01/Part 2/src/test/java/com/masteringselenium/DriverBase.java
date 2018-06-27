package com.masteringselenium;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

public class DriverBase {

    private static ThreadLocal<DriverFactory> driverThread;

    @BeforeSuite(alwaysRun = true)
    public static void instantiateDriverObject() {
        driverThread = new ThreadLocal<DriverFactory>() {
            @Override
            protected DriverFactory initialValue() {
                return new DriverFactory();
            }
        };
    }

    static WebDriver getDriver() {
        return driverThread.get().getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public static void quitDriver() {
        driverThread.get().quitDriver();
    }
}