package com.masteringselenium.tests;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProxyBasedIT {

    private static WebDriver driver;

    @AfterSuite
    public static void cleanUpDriver() {
        driver.quit();
    }

    @Test
    public void usingAProxyToTrackNetworkTrafficStep1() {
        BrowserMobProxy browserMobProxy = new BrowserMobProxyServer();
        browserMobProxy.start();
        Proxy seleniumProxyConfiguration = ClientUtil.createSeleniumProxy(browserMobProxy);

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability(CapabilityType.PROXY, seleniumProxyConfiguration);
        driver = new FirefoxDriver(firefoxOptions);
        browserMobProxy.newHar();
        driver.get("https://www.google.co.uk");
    }

    @Test
    public void usingAProxyToTrackNetworkTrafficStep2() {
        BrowserMobProxy browserMobProxy = new BrowserMobProxyServer();
        browserMobProxy.start();
        Proxy seleniumProxyConfiguration = ClientUtil.createSeleniumProxy(browserMobProxy);

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability(CapabilityType.PROXY, seleniumProxyConfiguration);
        driver = new FirefoxDriver(firefoxOptions);
        browserMobProxy.newHar();
        driver.get("https://www.google.co.uk");

        Har httpArchive = browserMobProxy.getHar();

        assertThat(getHTTPStatusCode("https://www.google.co.uk/", httpArchive))
                .isEqualTo(200);
    }

    private int getHTTPStatusCode(String expectedURL, Har httpArchive) {
        for (HarEntry entry : httpArchive.getLog().getEntries()) {
            if (entry.getRequest().getUrl().equals(expectedURL)) {
                return entry.getResponse().getStatus();
            }
        }

        return 0;
    }
}