package com.example.core.strategy;

import com.example.core.config.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeStrategy implements BrowserStrategy {
    @Override
    public WebDriver createDriver(Config cfg) {
        ChromeOptions options = new ChromeOptions();
        if (cfg.headless()) options.addArguments("--headless=new");
        options.addArguments("--start-maximized");
        return new ChromeDriver(options);
    }
}

