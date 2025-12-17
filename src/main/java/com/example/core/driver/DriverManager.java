package com.example.core.driver;

import com.example.core.config.Config;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Optional;

public final class DriverManager {

    private static final Logger LOG = LoggerFactory.getLogger(DriverManager.class);
    private static final ThreadLocal<WebDriver> threadLocalDriverInstance = new ThreadLocal<>();

    private DriverManager() {
    }

    public static void start(Config cfg) {
        String thread = Thread.currentThread().getName();
        LOG.info("Starting WebDriver on thread='{}' (browser={}, headless={})",
                thread, cfg.browser(), cfg.headless());
        WebDriver driver = WebDriverFactory.getWebDriver(cfg);
        threadLocalDriverInstance.set(driver);
        LOG.info("WebDriver started on thread='{}' → {}", thread, driver.getClass().getSimpleName());
    }

    public static WebDriver get() {
        String thread = Thread.currentThread().getName();
        LOG.debug("Getting WebDriver for thread='{}'", thread);
        return Objects.requireNonNull(threadLocalDriverInstance.get(), "Driver instance not started");
    }

    public static void quit() {
        String thread = Thread.currentThread().getName();
        Optional.ofNullable(threadLocalDriverInstance.get()).ifPresent(d -> {
            LOG.info("Quitting WebDriver on thread='{}' ({})", thread, d.getClass().getSimpleName());
            d.quit();
            threadLocalDriverInstance.remove();
            LOG.info("WebDriver quit and removed for thread='{}'", thread);
        });
    }
}

