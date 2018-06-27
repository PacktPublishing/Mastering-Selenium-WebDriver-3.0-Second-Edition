package com.masteringselenium.tests;

import com.masteringselenium.DriverBase;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static org.assertj.core.api.Assertions.assertThat;

public class InjectJavaScriptLibraryIT extends DriverBase {

    private RemoteWebDriver driver;

    @BeforeMethod
    public void setup() throws MalformedURLException {
        driver = getDriver();
    }

    private void injectScript(String scriptURL) {
        driver.executeScript("function injectScript(url) {\n" +
                        "    var script = document.createElement('script');\n" +
                        "    script.src = url;\n" +
                        "    var head = document.getElementsByTagName('head')[0];\n" +
                        "    head.appendChild(script);\n" +
                        "}\n" +
                        "\n" +
                        "var scriptURL = arguments[0];\n" +
                        "injectScript(scriptURL);"
                , scriptURL);
    }

    private Boolean isjQueryLoaded() {
        return (Boolean) driver.executeScript("return typeof jQuery != 'undefined';");
    }

    private static ExpectedCondition<Boolean> jQueryHasLoaded() {
        return webDriver -> {
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            return Boolean.valueOf(js.executeScript("return typeof jQuery != 'undefined';").toString());
        };
    }

    @Test
    public void injectjQueryIntoGoogle() {
        WebDriverWait wait = new WebDriverWait(driver, 15, 100);

        driver.get("http://www.google.com");

        assertThat(isjQueryLoaded()).isEqualTo(false);

        injectScript("https://code.jquery.com/jquery-latest.min.js");
        wait.until(jQueryHasLoaded());

        assertThat(isjQueryLoaded()).isEqualTo(true);
    }
}