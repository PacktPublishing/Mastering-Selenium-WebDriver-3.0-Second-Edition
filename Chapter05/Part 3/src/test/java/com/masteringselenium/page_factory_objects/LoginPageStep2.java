package com.masteringselenium.page_factory_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPageStep2 {

    @FindBy(how = How.ID, using = "username")
    private WebElement usernameField;

    @FindBy(how = How.ID, using = "password")
    private WebElement passwordField;

    @FindBy(how = How.ID, using = "login")
    private WebElement loginButton;

    public LoginPageStep2(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void logInWithUsernameAndPassword(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }
}