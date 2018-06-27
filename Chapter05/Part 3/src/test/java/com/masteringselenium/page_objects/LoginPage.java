package com.masteringselenium.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    public static By usernameLocator = By.id("username");
    public static By passwordLocator = By.id("password");
    public static By loginButtonLocator = By.id("login");

    public static void logInWithUsernameAndPassword(String username, String password, WebDriver driver) {

        driver.findElement(usernameLocator).sendKeys(username);
        driver.findElement(passwordLocator).sendKeys(password);
        driver.findElement(loginButtonLocator).click();
    }
}