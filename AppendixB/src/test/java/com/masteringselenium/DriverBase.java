package com.masteringselenium;

import com.masteringselenium.config.DriverFactory;
import net.lightbody.bmp.BrowserMobProxy;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DriverBase {

    private static List<DriverFactory> webDriverThreadPool = Collections.synchronizedList(new ArrayList<DriverFactory>());
    private static ThreadLocal<DriverFactory> driverThread;

    @BeforeClass
    public static void instantiateDriverObject() {
        driverThread = new ThreadLocal<DriverFactory>() {
            @Override
            protected DriverFactory initialValue() {
                DriverFactory webDriverThread = new DriverFactory();
                webDriverThreadPool.add(webDriverThread);
                return webDriverThread;
            }
        };
    }

    public static RemoteWebDriver getBrowserMobProxyEnabledDriver() throws MalformedURLException {
        return driverThread.get().getDriver(true);
    }

    public static RemoteWebDriver getDriver() throws MalformedURLException {
        return driverThread.get().getDriver();
    }

    public static BrowserMobProxy getBrowserMobProxy() {
        return driverThread.get().getBrowserMobProxy();
    }

    @After
    public void clearCookies() {
        try {
            getDriver().manage().deleteAllCookies();
        } catch (Exception ex) {
            System.err.println("Unable to delete cookies: " + ex);
        }
    }

    @AfterClass
    public static void closeDriverObjects() {
        for (DriverFactory webDriverThread : webDriverThreadPool) {
            webDriverThread.quitDriver();
        }
    }
}