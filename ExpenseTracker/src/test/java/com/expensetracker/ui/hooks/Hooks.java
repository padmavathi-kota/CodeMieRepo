package com.expensetracker.ui.hooks;

import com.expensetracker.ui.driver.DriverFactory;
import com.expensetracker.ui.utils.ScreenshotUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before
    public void beforeScenario() {
        DriverFactory.initDriver();
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] png = ScreenshotUtils.takeScreenshotBytes();
            scenario.attach(png, "image/png", "failure-screenshot");
        }
        DriverFactory.quitDriver();
    }
}
