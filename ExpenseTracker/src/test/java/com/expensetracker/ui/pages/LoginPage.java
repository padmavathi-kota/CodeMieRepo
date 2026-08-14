package com.expensetracker.ui.pages;

import com.expensetracker.ui.config.TestConfig;
import com.expensetracker.ui.driver.DriverFactory;
import com.expensetracker.ui.utils.WaitUtils;
import org.openqa.selenium.By;

public class LoginPage {

    private final By username = By.id("username");
    private final By password = By.id("password");
    private final By submit = By.id("login-submit");

    // Generic Spring Security error parameter often: ?error
    // If your template shows a specific div, replace locator accordingly.
    private final By errorContainer = By.cssSelector("[class*='error'], .alert, .alert-danger");

    public void open() {
        DriverFactory.getDriver().get(TestConfig.getBaseUrl() + "/login");
    }

    public void login(String user, String pass) {
        WaitUtils.visible(username).clear();
        WaitUtils.visible(username).sendKeys(user);

        WaitUtils.visible(password).clear();
        WaitUtils.visible(password).sendKeys(pass);

        WaitUtils.clickable(submit).click();
    }

    public boolean isAt() {
        return DriverFactory.getDriver().getCurrentUrl().contains("/login");
    }

    public boolean isErrorVisible() {
        try {
            return WaitUtils.visible(errorContainer).isDisplayed();
        } catch (Exception e) {
            // fallback: if /login?error is used
            return DriverFactory.getDriver().getCurrentUrl().contains("error");
        }
    }
}
