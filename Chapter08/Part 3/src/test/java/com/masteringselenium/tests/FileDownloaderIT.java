package com.masteringselenium.tests;

import com.masteringselenium.DriverBase;
import com.masteringselenium.downloader.FileDownloader;
import com.masteringselenium.downloader.RequestType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URI;

import static com.masteringselenium.downloader.CheckFileHash.generateHashForFileOfType;
import static com.masteringselenium.downloader.HashType.MD5;
import static com.masteringselenium.downloader.HashType.SHA1;
import static org.assertj.core.api.Assertions.assertThat;

public class FileDownloaderIT extends DriverBase {

    private RemoteWebDriver driver;

    @BeforeSuite
    public void setup() throws Exception {
        driver = getDriver();
    }


    @Test
    public void downloadAFile() throws Exception {
        FileDownloader downloadHandler = new FileDownloader(driver);
        driver.get("http://web.masteringselenium.com/downloadTest.html");
        WebElement fileThatShouldExist = driver.findElement(By.id("fileToDownload"));
        URI fileAsURI = new URI(fileThatShouldExist.getAttribute("href"));

        downloadHandler.setURI(fileAsURI);
        downloadHandler.setHTTPRequestMethod(RequestType.GET);

        File downloadedFile = downloadHandler.downloadFile();

        assertThat(downloadedFile.exists()).isEqualTo(true);
        assertThat(downloadHandler.getLinkHTTPStatus()).isEqualTo(200);
    }

    @Test
    public void downloadAFileWhilstMimickingSeleniumCookiesAndCheckTheSHA1Hash() throws Exception {
        FileDownloader downloadHandler = new FileDownloader(driver);
        driver.get("http://web.masteringselenium.com/downloadTest.html");
        WebElement fileThatShouldExist = driver.findElement(By.id("fileToDownload"));
        URI fileAsURI = new URI(fileThatShouldExist.getAttribute("href"));

        downloadHandler.setURI(fileAsURI);
        downloadHandler.setHTTPRequestMethod(RequestType.GET);
        File downloadedFile = downloadHandler.downloadFile();

        assertThat(downloadedFile.exists()).isEqualTo(true);
        assertThat(downloadHandler.getLinkHTTPStatus()).isEqualTo(200);
        assertThat(generateHashForFileOfType(downloadedFile, SHA1))
                .isEqualTo("8882e3d972be82e14a98c522745746a03b97997a");
    }

    @Test
    public void downloadAFileWhilstMimickingSeleniumCookiesAndCheckTheMD5Hash() throws Exception {
        FileDownloader downloadHandler = new FileDownloader(driver);
        driver.get("http://web.masteringselenium.com/downloadTest.html");
        WebElement fileThatShouldExist = driver.findElement(By.id("fileToDownload"));
        URI fileAsURI = new URI(fileThatShouldExist.getAttribute("href"));

        downloadHandler.setURI(fileAsURI);
        downloadHandler.setHTTPRequestMethod(RequestType.GET);
        File downloadedFile = downloadHandler.downloadFile();

        assertThat(downloadedFile.exists()).isEqualTo(true);
        assertThat(downloadHandler.getLinkHTTPStatus()).isEqualTo(200);
        assertThat(generateHashForFileOfType(downloadedFile, MD5))
                .isEqualTo("d1f296f523b74462b31b912a5675a814");
    }
}