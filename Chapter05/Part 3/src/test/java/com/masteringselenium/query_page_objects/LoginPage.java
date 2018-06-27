package com.masteringselenium.query_page_objects;

import com.lazerycode.selenium.util.Query;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.BrowserType;

public class LoginPage extends BasePage {

    private Query usernameField = new Query(By.id("username"), driver);
    private Query passwordField = new Query(By.id("password"), driver);
    private Query loginButton = new Query(By.id("login"), driver);

    public void logInWithUsernameAndPassword(String username, String password) {
        usernameField.findWebElement().sendKeys(username);
        passwordField.findWebElement().sendKeys(password);
        loginButton.findWebElement().click();

        loginButton.addAlternateLocator(BrowserType.IE, By.id("only_in_ie"));
    }
}
