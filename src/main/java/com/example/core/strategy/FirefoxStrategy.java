package com.example.core.strategy;

import com.example.core.config.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxStrategy implements BrowserStrategy {
    @Override
    public WebDriver createDriver(Config cfg) {
        FirefoxOptions options = new FirefoxOptions();
        if (cfg.headless()) options.addArguments("-headless");
        return new FirefoxDriver(options);
    }
}

