package com.masteringselenium.tests;

import com.masteringselenium.AppiumBase;
import com.masteringselenium.page_objects.CalculatorPageObject;
import io.appium.java_client.android.Activity;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CalculatorIT extends AppiumBase {

    @BeforeMethod
    public void setCorrectActivity() throws Exception {
        String appPackage = "com.sec.android.app.popupcalculator";
        String appActivity = ".Calculator";
        getDriver(new Activity(appPackage, appActivity));
    }

    @Test
    public void AddNumbersTogether() {
        CalculatorPageObject calculatorPageObject = new CalculatorPageObject();

        String result = calculatorPageObject.enterNumber("100")
                .add()
                .enterNumber("27")
                .equals();

        assertThat(result).isEqualTo("127");
    }
}
