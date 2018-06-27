package com.masteringselenium.tests;

import com.masteringselenium.DriverBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class InteractiveJavaScriptIT extends DriverBase {

    private RemoteWebDriver driver;

    @BeforeMethod
    public void setup() throws MalformedURLException {
        driver = DriverBase.getDriver();
    }

    @Test
    public void interactiveCallback() {
        driver.manage().timeouts().setScriptTimeout(45, TimeUnit.SECONDS);
        driver.get("http://www.google.com");

        String searchTerms = (String) driver.executeAsyncScript("window.callback = arguments[arguments.length - 1];\n" +
                "var dataInput = document.createElement('div');\n" +
                "dataInput.id = \"se_temp_markup\";\n" +
                "dataInput.setAttribute(\"style\", \"width: 200px; height: 100px; background-color: yellow; z-index: 99999999; position: fixed; padding: 1rem;\");\n" +
                "dataInput.innerHTML = \"<p>Enter some text to use in the Selenium test:</p>\\n\" +\n" +
                "    \"<input type=\\\"text\\\" id=\\\"setest_collect_data\\\">\\n\" +\n" +
                "    \"<button onclick=\\\"returnDataToSelenium()\\\">submit</button>\";\n" +
                "\n" +
                "\n" +
                "var script = document.createElement('script');\n" +
                "script.innerHTML = \"function returnDataToSelenium() {\\n\" +\n" +
                "    \"    var userInput = document.getElementById('setest_collect_data').value;\\n\" +\n" +
                "    \"    document.getElementById(\\\"se_temp_markup\\\").remove();\\n\" +\n" +
                "    \"    window.callback(userInput);\\n\" +\n" +
                "    \"}\";\n" +
                "\n" +
                "var body = document.getElementsByTagName('body')[0];\n" +
                "body.appendChild(script);\n" +
                "body.appendChild(dataInput);");

        WebElement googleSearchBox = driver.findElement(By.name("q"));
        googleSearchBox.sendKeys(searchTerms);
        googleSearchBox.submit();
    }
}
