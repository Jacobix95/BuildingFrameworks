package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

    @FindBy(css = "h1")
    private WebElement header;

    @FindBy(css = "a.logout")
    private WebElement logoutLink;

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    public String getHeaderText() {
        waitUntilVisible(header, 10);
        return header.getText();
    }

    public MyAccountPage waitUntilLoaded() {
        waitUntilVisible(header, 10);
        return this;
    }

    public AuthPage clickSignOut() {
        click(logoutLink);
        return new AuthPage(driver);
    }
}

