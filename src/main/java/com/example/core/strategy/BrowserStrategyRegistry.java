package com.example.core.strategy;

import java.util.Map;

public enum BrowserStrategyRegistry {
    INSTANCE;

    private final Map<String, BrowserStrategy> strategies = Map.of(
            "chrome", new ChromeStrategy(),
            "firefox", new FirefoxStrategy(),
            "edge", new EdgeStrategy()
    );

    public BrowserStrategy get(String browserKey) {
        BrowserStrategy strategy = strategies.get(browserKey.toLowerCase());
        if (strategy == null) {
            throw new IllegalArgumentException("Unsupported browser: " + browserKey);
        }
        return strategy;
    }
}

