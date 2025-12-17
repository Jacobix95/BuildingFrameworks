package com.example.core.strategy;

import com.example.core.config.Config;
import org.openqa.selenium.WebDriver;

public interface BrowserStrategy {
    WebDriver createDriver(Config cfg);
}

