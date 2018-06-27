package com.masteringselenium.page_factory_objects;

import com.masteringselenium.DriverBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private static final String USERNAME_LOCATOR = "username";

    @FindBy(how = How.ID, using = USERNAME_LOCATOR)
    private WebElement usernameField;

    @FindBy(how = How.ID, using = "password")
    private WebElement passwordField;

    @FindBy(how = How.ID, using = "login")
    private WebElement loginButton;

    public LoginPage() throws Exception {
        PageFactory.initElements(DriverBase.getDriver(), this);
    }

    public void logInWithUsernameAndPassword(String username, String password) throws Exception {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();

        WebDriverWait wait = new WebDriverWait(DriverBase.getDriver(), 15, 100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(USERNAME_LOCATOR)));
    }
}