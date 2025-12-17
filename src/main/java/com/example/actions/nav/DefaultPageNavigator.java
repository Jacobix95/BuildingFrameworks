package com.example.actions.nav;

import com.example.pages.AuthPage;
import com.example.pages.HomePage;
import org.openqa.selenium.WebDriver;

public class DefaultPageNavigator implements PageNavigator {
    private final WebDriver driver;

    public DefaultPageNavigator(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public AuthPage goToAuthPage() {
        return new HomePage(driver).clickSignIn();
    }
}

