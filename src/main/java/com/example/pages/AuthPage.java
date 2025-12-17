package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthPage extends BasePage {


    @FindBy(xpath = "//input[@id='email']")
    private WebElement loginEmailField;

    @FindBy(id = "passwd")
    private WebElement loginPasswordField;

    @FindBy(id = "SubmitLogin")
    private WebElement loginButton;

    @FindBy(css = "#center_column .alert.alert-danger")
    private WebElement loginErrorAlert;

    public AuthPage(WebDriver driver) {
        super(driver);
    }

    public AuthPage typeEmail(String email) {
        type(loginEmailField, email);
        return this;
    }

    public AuthPage typePassword(String password) {
        type(loginPasswordField, password);
        return this;
    }

    public AuthPage clickSubmitButton() {
        click(loginButton);
        return this;
    }

    public MyAccountPage toMyAccountPage() {
        return new MyAccountPage(driver).waitUntilLoaded();
    }

    public String getLoginErrorMessage() {
        waitUntilVisible(loginErrorAlert, 15);
        return loginErrorAlert.getText();
    }

    public boolean isLoginFormVisible() {
        try {
            waitUntilVisible(loginEmailField, 15);
            waitUntilVisible(loginPasswordField, 15);
            waitUntilVisible(loginButton, 15);
            return loginEmailField.isDisplayed()
                    && loginPasswordField.isDisplayed()
                    && loginButton.isDisplayed();
        } catch (WebDriverException e) {
            return false;
        }
    }
}

