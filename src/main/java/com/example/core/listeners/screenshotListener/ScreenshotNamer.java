package com.example.core.listeners.screenshotListener;

public interface ScreenshotNamer {
    String fileName(String testClass, String method, long epochMilli);
}

