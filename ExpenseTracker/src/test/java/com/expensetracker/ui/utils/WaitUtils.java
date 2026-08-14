package com.expensetracker.ui.utils;

import com.expensetracker.ui.config.TestConfig;
import com.expensetracker.ui.driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private static WebDriverWait waitObj() {
        return new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(TestConfig.explicitWaitSeconds()));
    }

    public static WebElement visible(By by) {
        return waitObj().until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static WebElement clickable(By by) {
        return waitObj().until(ExpectedConditions.elementToBeClickable(by));
    }

    public static boolean urlContains(String partial) {
        return waitObj().until(ExpectedConditions.urlContains(partial));
    }
}
