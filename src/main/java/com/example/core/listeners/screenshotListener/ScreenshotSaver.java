package com.example.core.listeners.screenshotListener;

import org.openqa.selenium.TakesScreenshot;

import java.nio.file.Path;

public interface ScreenshotSaver {
    Path save(TakesScreenshot ts, Path directory, String fileName);
}

