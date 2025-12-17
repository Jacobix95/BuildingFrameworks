package com.example.core.listeners.screenshotListener;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.Instant;

public class DefaultScreenshotNamer implements ScreenshotNamer {
    private static final DateTimeFormatter TS = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss")
            .withZone(ZoneId.systemDefault());

    @Override
    public String fileName(String testClass, String method, long epochMilli) {
        return method + "-" + TS.format(Instant.ofEpochMilli(epochMilli)) + ".png";
    }
}

