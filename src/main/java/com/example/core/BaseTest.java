package com.example.core;

import com.example.core.config.Config;
import com.example.core.config.ConfigLoader;
import com.example.core.driver.DriverManager;
import com.example.core.driver.DriverProvider;
import com.example.core.driver.ThreadLocalDriverProvider;
import com.example.core.listeners.screenshotListener.TestListener;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.util.Objects;

import static com.example.core.time.Timeouts.IMPLICIT;
import static com.example.core.time.Timeouts.PAGE_LOAD;

@Listeners(TestListener.class)
public abstract class BaseTest {

    protected final Logger LOG = LoggerFactory.getLogger(getClass());
    protected static final Config CFG = ConfigLoader.load();

    protected DriverProvider driverProvider = new ThreadLocalDriverProvider();

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        LOG.info("=== Test setup start ===");
        LOG.info("Config → env: {}, baseUrl: {}, browser: {}, headless: {}",
                System.getProperty("env", "dev"), CFG.baseUrl(), CFG.browser(), CFG.headless());

        DriverManager.start(CFG);

        this.driver = driverProvider.current()
                .orElseThrow(() -> new IllegalStateException("No WebDriver available from DriverProvider"));

        driver.manage().timeouts()
                .implicitlyWait(IMPLICIT)
                .pageLoadTimeout(PAGE_LOAD);

        beforeNavigation(driver, CFG);

        LOG.info("Navigating to: {}", CFG.baseUrl());
        driver.get(Objects.requireNonNull(CFG.baseUrl(), "baseUrl must not be null"));

        afterNavigation(driver, CFG);

        LOG.info("Current URL after navigation: {}", driver.getCurrentUrl());
        LOG.info("=== Test setup done ===");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        LOG.info("=== Test teardown start ===");
        DriverManager.quit();
        driver = null;
        LOG.info("Driver quit and cleared. === Test teardown done ===");
    }

    protected void beforeNavigation(WebDriver driver, Config config) {
    }

    protected void afterNavigation(WebDriver driver, Config config) {
    }
}
