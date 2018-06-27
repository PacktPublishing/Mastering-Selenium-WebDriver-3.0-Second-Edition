package com.masteringselenium.config;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;

import static com.masteringselenium.config.DriverType.FIREFOX;
import static com.masteringselenium.config.DriverType.valueOf;
import static org.openqa.selenium.Proxy.ProxyType.MANUAL;
import static org.openqa.selenium.remote.CapabilityType.PROXY;

public class DriverFactory {

    private RemoteWebDriver webDriver;
    private DriverType selectedDriverType;

    private final String operatingSystem = System.getProperty("os.name").toUpperCase();
    private final String systemArchitecture = System.getProperty("os.arch");
    private final boolean useRemoteWebDriver = Boolean.getBoolean("remoteDriver");
    private final boolean proxyEnabled = Boolean.getBoolean("proxyEnabled");
    private final String proxyHostname = System.getProperty("proxyHost");
    private final Integer proxyPort = Integer.getInteger("proxyPort");
    private final String proxyDetails = String.format("%s:%d", proxyHostname, proxyPort);
    private BrowserMobProxy browserMobProxy;
    private boolean usingBrowserMobProxy = false;

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

    public BrowserMobProxy getBrowserMobProxy() {
        if (usingBrowserMobProxy) {
            return browserMobProxy;
        }
        return null;
    }

    public RemoteWebDriver getDriver(boolean useBrowserMobProxy) throws MalformedURLException {
        if (useBrowserMobProxy != usingBrowserMobProxy) {
            quitDriver();
        }
        if (null == webDriver) {
            instantiateWebDriver(selectedDriverType, useBrowserMobProxy);
        }

        return webDriver;
    }

    public RemoteWebDriver getDriver() throws MalformedURLException {
        return getDriver(usingBrowserMobProxy);
    }

    public void quitDriver() {
        if (null != webDriver) {
            webDriver.quit();
            webDriver = null;
            usingBrowserMobProxy = false;
        }
    }

    private void instantiateWebDriver(DriverType driverType, boolean useBrowserMobProxy) throws MalformedURLException {
        System.out.println(" ");
        System.out.println("Local Operating System: " + operatingSystem);
        System.out.println("Local Architecture: " + systemArchitecture);
        System.out.println("Selected Browser: " + selectedDriverType);
        System.out.println("Connecting to Selenium Grid: " + useRemoteWebDriver);
        System.out.println(" ");

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        if (proxyEnabled || useBrowserMobProxy) {
            Proxy proxy;
            if (useBrowserMobProxy) {
                usingBrowserMobProxy = true;
                browserMobProxy = new BrowserMobProxyServer();
                browserMobProxy.start();
                if (proxyEnabled) {
                    browserMobProxy.setChainedProxy(new InetSocketAddress(proxyHostname, proxyPort));
                }
                proxy = ClientUtil.createSeleniumProxy(browserMobProxy);
            } else {
                proxy = new Proxy();
                proxy.setProxyType(MANUAL);
                proxy.setHttpProxy(proxyDetails);
                proxy.setSslProxy(proxyDetails);
            }
            desiredCapabilities.setCapability(PROXY, proxy);
        }

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