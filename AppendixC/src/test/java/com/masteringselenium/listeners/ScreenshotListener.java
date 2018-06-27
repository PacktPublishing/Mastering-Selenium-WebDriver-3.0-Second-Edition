package com.masteringselenium.listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.masteringselenium.AppiumBase.getDriver;

public class ScreenshotListener extends TestListenerAdapter {

    private static boolean createFile(File screenshot) {
        boolean fileCreated = false;

        if (screenshot.exists()) {
            fileCreated = true;
        } else {
            File parentDirectory = new File(screenshot.getParent());
            if (parentDirectory.exists() || parentDirectory.mkdirs()) {
                try {
                    fileCreated = screenshot.createNewFile();
                } catch (IOException errorCreatingScreenshot) {
                    errorCreatingScreenshot.printStackTrace();
                }
            }
        }

        return fileCreated;
    }

    private static void writeScreenshotToFile(WebDriver driver, File screenshot) {
        try {
            FileOutputStream screenshotStream = new FileOutputStream(screenshot);
            screenshotStream.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
            screenshotStream.close();
        } catch (IOException unableToWriteScreenshot) {
            System.err.println("Unable to write " + screenshot.getAbsolutePath());
            unableToWriteScreenshot.printStackTrace();
        }
    }

    public static void takeScreenshot(WebDriver driver, String filename) {
        String screenshotDirectory = System.getProperty("screenshotDirectory", "build/screenshots");
        String screenshotAbsolutePath = screenshotDirectory + File.separator + System.currentTimeMillis() + "_" + filename + ".png";
        File screenshot = new File(screenshotAbsolutePath);
        if (createFile(screenshot)) {
            try {
                writeScreenshotToFile(driver, screenshot);
            } catch (ClassCastException weNeedToAugmentOurDriverObject) {
                writeScreenshotToFile(new Augmenter().augment(driver), screenshot);
            }
            System.out.println("Written screenshot to " + screenshotAbsolutePath);
        } else {
            System.err.println("Unable to create " + screenshotAbsolutePath);
        }
    }

    @Override
    public void onTestFailure(ITestResult failingTest) {
        try {
            takeScreenshot(getDriver(), failingTest.getName());
        } catch (Exception ex) {
            System.err.println("Unable to capture screenshot...");
            ex.printStackTrace();
        }
    }
}