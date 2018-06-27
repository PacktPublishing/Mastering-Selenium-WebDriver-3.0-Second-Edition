package com.masteringselenium.fluent_page_objects;

import com.lazerycode.selenium.util.Query;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private Query usernameField = new Query(By.id("username"), driver);
    private Query passwordField = new Query(By.id("password"), driver);
    private Query loginButton = new Query(By.id("login"), driver);

    public LoginPage enterUsername(String username) {
        usernameField.findWebElement().sendKeys(username);

        return this;
    }

    public LoginPage enterPassword(String password) {
        passwordField.findWebElement().sendKeys(password);

        return this;
    }


    public LoginPage andLogin() {
        loginButton.findWebElement().click();

        return this;
    }

    public void andFailLogin() {
        loginButton.findWebElement().click();
    }

    public IndexPage andSuccessfullyLogin() {
        loginButton.findWebElement().click();

        return new IndexPage();
    }

    public ChangePasswordPage andGoToChangePasswordPage() {

        return new ChangePasswordPage();
    }

    public IndexPage successfully() {

        return new IndexPage();
    }
}

