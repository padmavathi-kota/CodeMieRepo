package com.expensetracker.ui.pages;

import com.expensetracker.ui.config.TestConfig;
import com.expensetracker.ui.driver.DriverFactory;

public class ReportsPage {

    public void openChart() {
        DriverFactory.getDriver().get(TestConfig.getBaseUrl() + "/chart");
    }

    public void openGetChart() {
        DriverFactory.getDriver().get(TestConfig.getBaseUrl() + "/getchart");
    }

    public boolean isAtReportPage() {
        String url = DriverFactory.getDriver().getCurrentUrl();
        return url.contains("/chart") || url.contains("/getchart");
    }
}
