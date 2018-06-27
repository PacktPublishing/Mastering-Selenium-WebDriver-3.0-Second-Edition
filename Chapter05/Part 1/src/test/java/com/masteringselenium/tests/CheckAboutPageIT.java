package com.masteringselenium.tests;

import com.masteringselenium.DriverBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CheckAboutPageIT extends DriverBase {

    private WebDriver driver;

    @BeforeMethod
    public void setup() throws MalformedURLException {
        driver = getDriver();
    }

    @Test
    public void goToTheAboutPageStep1() {
        driver.get("http://web.masteringselenium.com/index.html");
        driver.findElement(By.cssSelector(".left-footer > a")).click();
        WebElement element = driver.findElement(By.cssSelector("h1"));

        assertThat(element.getText()).isEqualTo("About us!");
    }

    @Test
    public void checkThatAboutPageHasText() {
        driver.get("http://web.masteringselenium.com/index.html");
        driver.findElement(By.cssSelector("footer div:nth-child(1) > a")).click();
        String titleText = driver.findElement(By.cssSelector(".container > div h1")).getText();

        assertThat(titleText).isEqualTo("About us!");
    }

    @Test
    public void goToTheAboutPageStep2() {
        driver.get("http://web.masteringselenium.com/index.html");

        WebElement aboutLink = driver.findElement(By.cssSelector(".left-footer > a"));

        aboutLink.click();

        WebElement aboutHeading = driver.findElement(By.cssSelector("h1"));

        assertThat(aboutHeading.getText()).isEqualTo("About us!");
    }

    @Test
    public void goToTheAboutPageStep3() {
        driver.get("http://web.masteringselenium.com/index.html");

        WebElement aboutLink = driver.findElement(IndexPage.aboutLinkLocator);

        aboutLink.click();

        WebElement aboutHeading = driver.findElement(AboutPage.aboutHeadingLocator);

        assertThat(aboutHeading.getText()).isEqualTo("About us!");
    }
}
