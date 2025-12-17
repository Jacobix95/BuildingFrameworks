package com.example.core.strategy;

import com.example.core.config.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeStrategy implements BrowserStrategy {
    @Override
    public WebDriver createDriver(Config cfg) {
        EdgeOptions options = new EdgeOptions();
        if (cfg.headless()) options.addArguments("--headless=new");
        return new EdgeDriver(options);
    }
}

