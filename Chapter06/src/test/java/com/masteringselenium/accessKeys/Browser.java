package com.masteringselenium.accessKeys;

import org.openqa.selenium.remote.BrowserType;

public enum Browser {
    FIREFOX(BrowserType.FIREFOX),
    GOOGLECHROME(BrowserType.CHROME),
    SAFARI(BrowserType.SAFARI),
    OPERA(BrowserType.OPERA_BLINK),
    IE(BrowserType.IEXPLORE),
    EDGE(BrowserType.EDGE);

    private String type;

    Browser(String type) {
        this.type = type;
    }

    public static Browser getBrowserType(String browserName) {
        for (Browser browser : values()) {
            if (browserName.toLowerCase().contains(browser.type)) {
                return browser;
            }
        }

        throw new IllegalArgumentException("Unrecognised browser name '" + browserName + "'");
    }
}
