package com.example.core.decorator;

import org.openqa.selenium.WebDriver;

public interface DriverDecorator {
    WebDriver decorate(WebDriver driver);
}

