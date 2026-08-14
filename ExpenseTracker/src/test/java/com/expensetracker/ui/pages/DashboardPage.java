package com.expensetracker.ui.pages;

import com.expensetracker.ui.config.TestConfig;
import com.expensetracker.ui.driver.DriverFactory;

public class DashboardPage {

    public boolean isAt() {
        return DriverFactory.getDriver().getCurrentUrl().equals(TestConfig.getBaseUrl() + "/dashboard")
                || DriverFactory.getDriver().getCurrentUrl().contains("/dashboard");
    }
}
