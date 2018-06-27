package com.masteringselenium;

import com.masteringselenium.config.AppiumFactory;
import com.masteringselenium.listeners.ScreenshotListener;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.Activity;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Listeners(ScreenshotListener.class)
public class AppiumBase {

    private static List<AppiumFactory> webDriverThreadPool = Collections.synchronizedList(new ArrayList<AppiumFactory>());
    private static ThreadLocal<AppiumFactory> appiumFactory;

    @BeforeSuite
    public static void instantiateDriverObject() {
        appiumFactory = new ThreadLocal<AppiumFactory>() {
            @Override
            protected AppiumFactory initialValue() {
                AppiumFactory appiumFactory = new AppiumFactory();
                webDriverThreadPool.add(appiumFactory);
                return appiumFactory;
            }
        };
    }

    public static AppiumDriver getDriver() throws Exception {
        return appiumFactory.get().getDriver();
    }

    public static AppiumDriver getDriver(Activity desiredActivity) throws Exception {
        return appiumFactory.get().getDriver(desiredActivity);
    }

    @AfterSuite(alwaysRun = true)
    public static void closeDriverObjects() {
        for (AppiumFactory appiumFactory : webDriverThreadPool) {
            appiumFactory.quitDriver();
        }
    }
}
