package com.masteringselenium.tests;

import com.masteringselenium.DriverBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static com.masteringselenium.tests.CalculateOffsetPosition.CursorPosition;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class dragAndDropIT extends DriverBase {

    private RemoteWebDriver driver;

    @BeforeTest
    public void bindDriver() throws MalformedURLException {
        driver = getDriver();
    }

    @Test
    public void automateJavaScriptDragAndDrop() {
        driver.get("http://web.masteringselenium.com/jsDragAndDrop.html");
        Actions advancedActions = new Actions(driver);
        final By destroyableBoxes = By.cssSelector("ul > li > div");
        WebElement obliterator = driver.findElement(By.id("obliterate"));
        WebElement firstBox = driver.findElement(By.id("one"));
        WebElement secondBox = driver.findElement(By.id("two"));

        assertThat(driver.findElements(destroyableBoxes).size()).isEqualTo(5);

        advancedActions.clickAndHold(firstBox)
                .moveToElement(obliterator)
                .release()
                .perform();

        assertThat(driver.findElements(destroyableBoxes).size()).isEqualTo(4);

        advancedActions.dragAndDrop(secondBox, obliterator).perform();

        assertThat(driver.findElements(destroyableBoxes).size()).isEqualTo(3);
    }

    @Test
    public void automateJavaScriptDragAndDropWithOffsetsStep1() {
        driver.get("http://web.masteringselenium.com/jsDragAndDropWithHandle.html");
        Actions advancedActions = new Actions(driver);
        final By destroyableBoxes = By.cssSelector("ul > li > div");
        WebElement obliterator = driver.findElement(By.id("obliterate"));
        WebElement firstBox = driver.findElement(By.id("one"));

        assertThat(driver.findElements(destroyableBoxes).size()).isEqualTo(5);

        advancedActions.moveToElement(firstBox)
                .moveByOffset(-40, 0)
                .clickAndHold()
                .moveToElement(obliterator)
                .release()
                .perform();

        assertThat(driver.findElements(destroyableBoxes).size()).isEqualTo(4);
    }

    @Test
    public void automateJavaScriptDragAndDropWithOffsetsStep2() {
        driver.get("http://web.masteringselenium.com/jsDragAndDropWithHandle.html");
        Actions advancedActions = new Actions(driver);
        final By destroyableBoxes = By.cssSelector("ul > li > div");
        WebElement obliterator = driver.findElement(By.id("obliterate"));
        WebElement firstBox = driver.findElement(By.id("one"));

        assertThat(driver.findElements(destroyableBoxes).size()).isEqualTo(5);

        advancedActions.moveToElement(firstBox)
                .moveByOffset(-40, 0)
                .clickAndHold(firstBox)
                .moveToElement(obliterator)
                .release()
                .perform();

        assertThat(driver.findElements(destroyableBoxes).size()).isEqualTo(4);
        // Intentionally failing
    }

    @Test
    public void automateJavaScriptDragAndDropWithOffsetsStep3() {
        driver.get("http://web.masteringselenium.com/jsDragAndDropWithHandle.html");
        Actions advancedActions = new Actions(driver);
        final By destroyableBoxes = By.cssSelector("ul > li > div");
        WebElement obliterator = driver.findElement(By.id("obliterate"));
        WebElement firstBox = driver.findElement(By.id("one"));

        assertThat(driver.findElements(destroyableBoxes).size()).isEqualTo(5);

        advancedActions.moveToElement(firstBox, -40, 0)
                .clickAndHold()
                .moveToElement(obliterator)
                .release()
                .perform();

        assertThat(driver.findElements(destroyableBoxes).size()).isEqualTo(4);
    }

@Test
public void automateJavaScriptDragAndDropWithOffsetsStep4() {
    driver.get("http://web.masteringselenium.com/jsDragAndDropWithHandle.html");
    Actions advancedActions = new Actions(driver);
    final By destroyableBoxes = By.cssSelector("ul > li > div");
    WebElement obliterator = driver.findElement(By.id("obliterate"));
    WebElement firstBox = driver.findElement(By.id("one"));
    WebElement firstBoxText = driver.findElement(By.cssSelector("#one > span"));

    assertThat(driver.findElements(destroyableBoxes).size()).isEqualTo(5);

    CalculateOffsetPosition op =
            new CalculateOffsetPosition(firstBox, firstBoxText, CursorPosition.CENTER);

    advancedActions.moveToElement(firstBox)
            .moveByOffset(op.getXOffset(), op.getYOffset())
            .clickAndHold()
            .moveToElement(obliterator)
            .release()
            .perform();

    assertThat(driver.findElements(destroyableBoxes).size()).isEqualTo(4);
}
}
