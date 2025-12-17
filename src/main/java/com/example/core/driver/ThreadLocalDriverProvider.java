package com.example.core.driver;

import org.openqa.selenium.WebDriver;

import java.util.Optional;

public class ThreadLocalDriverProvider implements DriverProvider {
    @Override
    public Optional<WebDriver> current() {
        try {
            return Optional.ofNullable(DriverManager.get());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}

