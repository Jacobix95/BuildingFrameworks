package com.example.actions;

import com.example.actions.nav.DefaultPageNavigator;
import com.example.actions.nav.PageNavigator;
import com.example.data.User;
import com.example.pages.AuthPage;
import com.example.pages.MyAccountPage;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthFlow {

    private static final Logger LOG = LoggerFactory.getLogger(AuthFlow.class);

    private final PageNavigator navigator;

    public AuthFlow(WebDriver driver) {
        this(new DefaultPageNavigator(driver));
    }

    public AuthFlow(PageNavigator navigator) {
        this.navigator = navigator;
    }

    public MyAccountPage login(User user) {
        LOG.info("AuthFlow: starting login for user '{}'", user.email());

        AuthPage auth = navigator.goToAuthPage();

        MyAccountPage account = auth
                .typeEmail(user.email())
                .typePassword(user.password())
                .clickSubmitButton()
                .toMyAccountPage();

        LOG.info("AuthFlow: login finished → navigated to MyAccountPage (URL: {})",
                account.getCurrentUrl());
        return account;
    }
}

