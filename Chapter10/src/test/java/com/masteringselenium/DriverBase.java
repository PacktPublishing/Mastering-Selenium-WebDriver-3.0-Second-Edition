package com.masteringselenium;

import com.applitools.eyes.selenium.Eyes;
import com.masteringselenium.config.DriverFactory;
import com.masteringselenium.listeners.ScreenshotListener;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Listeners(ScreenshotListener.class)
public class DriverBase {

    private static List<DriverFactory> webDriverThreadPool = Collections.synchronizedList(new ArrayList<DriverFactory>());
    private static ThreadLocal<DriverFactory> driverThread;

    @BeforeSuite(alwaysRun = true)
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

@BeforeMethod(alwaysRun = true)
public static void setTestName(Method method) {
    driverThread.get().setTestName(method.getName());
}

    public static RemoteWebDriver getDriver() throws MalformedURLException {
        return driverThread.get().getDriver();
    }

public static Eyes openEyes() throws Exception {
    return driverThread.get().openEyes();
}

    @AfterMethod(alwaysRun = true)
    public static void clearCookies() {
        try {
            getDriver().manage().deleteAllCookies();
        } catch (Exception ignored) {
            System.out.println("Unable to clear cookies, driver object is not viable...");
        }
    }

@AfterSuite(alwaysRun = true)
public static void closeDriverObjects() {
    for (DriverFactory webDriverThread : webDriverThreadPool) {
        webDriverThread.quitDriver();
        webDriverThread.closeEyes();
    }
}
}