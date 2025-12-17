package com.example.core.driver;

import org.openqa.selenium.WebDriver;

import java.util.Optional;

public interface DriverProvider {
    Optional<WebDriver> current();
}

