package com.masteringselenium.page_objects;

import com.lazerycode.selenium.util.Query;
import io.appium.java_client.MobileBy;
import io.appium.java_client.touch.offset.ElementOption;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static io.appium.java_client.touch.TapOptions.tapOptions;

public class CalculatorPageObject extends BasePageObject {

    private Query one = new Query(MobileBy.AccessibilityId("1"), driver);
    private Query two = new Query(MobileBy.AccessibilityId("2"), driver);
    private Query three = new Query(MobileBy.AccessibilityId("3"), driver);
    private Query four = new Query(MobileBy.AccessibilityId("4"), driver);
    private Query five = new Query(MobileBy.AccessibilityId("5"), driver);
    private Query six = new Query(MobileBy.AccessibilityId("6"), driver);
    private Query seven = new Query(MobileBy.AccessibilityId("7"), driver);
    private Query eight = new Query(MobileBy.AccessibilityId("8"), driver);
    private Query nine = new Query(MobileBy.AccessibilityId("9"), driver);
    private Query zero = new Query(MobileBy.AccessibilityId("0"), driver);
    private Query addButton = new Query(MobileBy.AccessibilityId("Plus"), driver);
    private Query subtractButton = new Query(MobileBy.AccessibilityId("Minus"), driver);
    private Query equalsButton = new Query(MobileBy.AccessibilityId("Equal"), driver);
    private Query result = new Query(MobileBy.id("com.sec.android.app.popupcalculator:id/txtCalc"), driver);

    private final Map<Character, Query> NUMBERS = Collections.unmodifiableMap(
            new HashMap<Character, Query>() {{
                put('1', one);
                put('2', two);
                put('3', three);
                put('4', four);
                put('5', five);
                put('6', six);
                put('7', seven);
                put('8', eight);
                put('9', nine);
                put('0', zero);
            }});

    public CalculatorPageObject enterNumber(String number) {
        for (Character digit : number.toCharArray()) {
            touchAction.tap(tapOptions().withElement(ElementOption.element(NUMBERS.get(digit).findMobileElement()))).perform();
        }

        return this;
    }

    public CalculatorPageObject add() {
        touchAction.tap(tapOptions().withElement(ElementOption.element(addButton.findMobileElement()))).perform();

        return this;
    }

    public CalculatorPageObject subtract() {
        touchAction.tap(tapOptions().withElement(ElementOption.element(subtractButton.findMobileElement()))).perform();

        return this;
    }

    public String equals() {
        touchAction.tap(tapOptions().withElement(ElementOption.element(equalsButton.findMobileElement()))).perform();

        return result.findMobileElement().getText();
    }

}
