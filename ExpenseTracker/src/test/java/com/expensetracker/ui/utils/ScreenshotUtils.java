package com.expensetracker.ui.utils;

import com.expensetracker.ui.driver.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.Base64;

public class ScreenshotUtils {

    public static byte[] takeScreenshotBytes() {
        return ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public static String takeScreenshotBase64() {
        byte[] bytes = takeScreenshotBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }
}
