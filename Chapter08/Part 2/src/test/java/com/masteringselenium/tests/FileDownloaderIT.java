package com.masteringselenium.tests;

import com.masteringselenium.DriverBase;
import com.masteringselenium.downloader.FileDownloader;
import com.masteringselenium.downloader.RequestType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

public class FileDownloaderIT extends DriverBase {

    private RemoteWebDriver driver;

    @BeforeSuite
    public void setup() throws MalformedURLException {
        driver = getDriver();
    }

    @Test
    public void statusCodeFromEmbeddedFile() throws Exception {
        FileDownloader downloadHandler = new FileDownloader(driver);
        driver.get("http://web.masteringselenium.com/downloadTest.html");
        WebElement fileThatShouldExist = driver.findElement(By.id("fileToDownload"));
        URI fileAsURI = new URI(fileThatShouldExist.getAttribute("href"));

        downloadHandler.setURI(fileAsURI);
        downloadHandler.setHTTPRequestMethod(RequestType.GET);

        assertThat(downloadHandler.getLinkHTTPStatus()).isEqualTo(200);
    }

    @Test
    public void statusCodeFromEmbeddedImage() throws Exception {
        FileDownloader downloadHandler = new FileDownloader(driver);
        driver.get("http://web.masteringselenium.com/downloadTest.html");
        WebElement fileThatShouldExist = driver.findElement(By.id("anImage"));
        URI fileAsURI = new URI(fileThatShouldExist.getAttribute("src"));

        downloadHandler.setURI(fileAsURI);
        downloadHandler.setHTTPRequestMethod(RequestType.GET);

        assertThat(downloadHandler.getLinkHTTPStatus()).isEqualTo(200);
        //Intentionally failing
    }
}