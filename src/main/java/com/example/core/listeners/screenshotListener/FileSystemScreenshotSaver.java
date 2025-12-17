package com.example.core.listeners.screenshotListener;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.nio.file.*;

public class FileSystemScreenshotSaver implements ScreenshotSaver {
    @Override
    public Path save(TakesScreenshot ts, Path directory, String fileName) {
        try {
            Files.createDirectories(directory);

            if (!fileName.toLowerCase().endsWith(".png")) {
                fileName = fileName + ".png";
            }

            File src = ts.getScreenshotAs(OutputType.FILE);
            Path target = directory.resolve(fileName);

            Files.copy(src.toPath(), target, StandardCopyOption.REPLACE_EXISTING);

            return target;
        } catch (Exception e) {
            throw new RuntimeException("Failed to save screenshot", e);
        }
    }
}
