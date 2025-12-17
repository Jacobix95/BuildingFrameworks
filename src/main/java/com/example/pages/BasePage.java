package com.example.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

import static com.example.core.time.Timeouts.EXPLICIT_DEFAULT;

public abstract class BasePage {

    protected final Logger LOG = LoggerFactory.getLogger(getClass());
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, EXPLICIT_DEFAULT);
        PageFactory.initElements(driver, this);
        LOG.debug("Initialized page object: {}", getClass().getSimpleName());
    }

    protected void click(WebElement el) {
        try {
            LOG.debug("Waiting for element to be clickable: {}", el);
            wait.until(ExpectedConditions.elementToBeClickable(el)).click();
            LOG.info("Clicked on element: {}", el);
        } catch (Exception e) {
            LOG.error("Failed to click element: {}", el, e);
            throw e;
        }
    }

    protected void waitUntilVisible(WebElement el, int timeoutInSecond) {
        try {
            LOG.debug("Waiting up to {}s for visibility of: {}", timeoutInSecond, el);
            WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSecond));
            customWait.until(ExpectedConditions.visibilityOf(el));
            LOG.info("Element is visible: {}", el);
        } catch (Exception e) {
            LOG.error("Element not visible after {}s: {}", timeoutInSecond, el, e);
            throw e;
        }
    }

    protected void type(WebElement el, String text) {
        try {
            LOG.debug("Waiting for element to be visible before typing: {}", el);
            waitUntilVisible(el, 10);
            el.clear();
            el.sendKeys(text);
            LOG.info("Typed '{}' into element: {}", text, el);
        } catch (Exception e) {
            LOG.error("Failed to type '{}' into element: {}", text, el, e);
            throw e;
        }
    }

    protected void waitUntilAllVisible(List<WebElement> elements) {
        try {
            LOG.debug("Waiting for all elements to be visible (count: {})", elements.size());
            wait.until(ExpectedConditions.visibilityOfAllElements(elements));
            LOG.info("All elements are visible.");
        } catch (Exception e) {
            LOG.error("Failed waiting for all elements to become visible.", e);
            throw e;
        }
    }

    protected void scrollIntoViewCenter(WebElement el) {
        LOG.debug("Scroll into view (center): {}", el);
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center', inline:'nearest'})", el);
        LOG.debug("Scrolled into view: {}", el);
    }

    protected void waitForPageLoad() {
        LOG.debug("Wait for page load → document.readyState == 'complete'");
        wait.until(webDriver -> "complete".equals(((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState")));
        LOG.debug("Page load complete");
    }

    protected WebElement ensureClickable(WebElement el) {
        LOG.debug("Ensure clickable: {}", el);
        WebElement clickable = wait.until(ExpectedConditions.elementToBeClickable(el));
        LOG.debug("Element is clickable: {}", clickable);
        return clickable;
    }

    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        LOG.debug("Current URL: {}", url);
        return url;
    }
}

