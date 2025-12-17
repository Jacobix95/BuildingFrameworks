package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(css = "a.login")
    private WebElement signInLink;

    @FindBy(xpath = "//a[@title='Women']")
    private WebElement womenCategory;

    @FindBy(css = "a[title='Contact us']")
    private WebElement contactUsLink;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public AuthPage clickSignIn() {
        click(signInLink);
        return new AuthPage(driver);
    }
}

