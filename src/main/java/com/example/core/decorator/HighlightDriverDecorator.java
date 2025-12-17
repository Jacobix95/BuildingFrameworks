package com.example.core.decorator;

import com.example.core.config.Config;
import com.example.core.listeners.highLighter.HighlightListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

public class HighlightDriverDecorator implements DriverDecorator {
    private final Config cfg;

    public HighlightDriverDecorator(Config cfg) {
        this.cfg = cfg;
    }

    @Override
    public WebDriver decorate(WebDriver driver) {
        if (!cfg.highlight()) return driver;
        return new EventFiringDecorator(new HighlightListener(driver, cfg)).decorate(driver);
    }
}

