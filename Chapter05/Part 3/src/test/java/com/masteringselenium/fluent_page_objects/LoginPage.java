package com.masteringselenium.fluent_page_objects;

import com.lazerycode.selenium.util.Query;
import com.masteringselenium.query_page_objects.BasePage;
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

//    public void andLogin() {
//        loginButton.findWebElement().click();
//
//        return this;
//    }

    public LoginPage andLogin() {
        loginButton.findWebElement().click();

        return this;
    }
}
//    public ChangePasswordPage andGoToChangePasswordPage() {
//
//        return new ChangePasswordPage();
//    }
//
//    public IndexPage successfully() {
//
//        return new IndexPage();
//    }

}
