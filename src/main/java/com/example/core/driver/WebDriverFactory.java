package com.example.core.driver;

import com.example.core.decorator.DriverDecoratorChain;
import com.example.core.config.Config;
import com.example.core.strategy.BrowserStrategy;
import com.example.core.strategy.BrowserStrategyRegistry;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class WebDriverFactory {

    private static final Logger LOG = LoggerFactory.getLogger(WebDriverFactory.class);

    private WebDriverFactory() {
    }

    public static WebDriver getWebDriver(Config cfg) {
        String browser = cfg.browser().toLowerCase();
        LOG.info("Creating WebDriver (browser='{}', headless={})", browser, cfg.headless());

        BrowserStrategy strategy = BrowserStrategyRegistry.INSTANCE.get(browser);

        try {
            WebDriver driver = strategy.createDriver(cfg);

            driver = DriverDecoratorChain.defaultChain(cfg).apply(driver);

            LOG.info("WebDriver created: {}", driver.getClass().getSimpleName());
            return driver;
        } catch (RuntimeException e) {
            LOG.error("Failed to create WebDriver for browser='{}'", browser, e);
            throw e;
        }
    }
}

