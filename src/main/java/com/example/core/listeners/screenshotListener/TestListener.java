package com.example.core.listeners.screenshotListener;

import com.example.core.driver.DriverProvider;
import com.example.core.driver.ThreadLocalDriverProvider;
import com.epam.reportportal.listeners.LogLevel;
import com.epam.reportportal.service.ReportPortal;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.io.File;
import java.nio.file.Path;
import java.time.Clock;
import java.util.Date;

public class TestListener implements IInvokedMethodListener {

    private static final Logger LOG = LoggerFactory.getLogger(TestListener.class);

    private final DriverProvider driverProvider = new ThreadLocalDriverProvider();
    private final ScreenshotSaver screenshotSaver = new FileSystemScreenshotSaver();
    private final ScreenshotNamer namer = new DefaultScreenshotNamer();
    private final Clock clock = Clock.systemDefaultZone();

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult result) {
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult result) {
        if (!method.isTestMethod()) {
            return;
        }

        if (result.getStatus() != ITestResult.FAILURE) {
            return;
        }

        WebDriver driver = driverProvider.current().orElse(null);
        if (!(driver instanceof TakesScreenshot ts)) {
            LOG.info("Screenshot skipped: driver null or no TakesScreenshot.");
            return;
        }

        String testClass = result.getTestClass().getRealClass().getSimpleName();
        String methodName = result.getMethod().getMethodName();
        long now = clock.millis();

        Path dir = Path.of(
                "artifacts",
                "screenshots",
                java.time.LocalDate.now(clock).toString(),
                testClass
        );

        String fileName = namer.fileName(testClass, methodName, now);
        Path screenshotPath = screenshotSaver.save(ts, dir, fileName);
        File screenshotFile = screenshotPath.toFile();

        try {
            ReportPortal.emitLog(
                    "Screenshot on failure",
                    LogLevel.ERROR.name(),
                    new Date(),
                    screenshotFile
            );
            LOG.info("RP_LOG: emitLog with attachment finished without exception");
        } catch (Exception e) {
            LOG.error("Failed to send screenshot to ReportPortal", e);
        }

        LOG.info("Screenshot saved locally at: {}", screenshotPath.toAbsolutePath());
    }
}
