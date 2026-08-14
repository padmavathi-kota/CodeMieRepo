package com.expensetracker.ui.pages;

import com.expensetracker.ui.config.TestConfig;
import com.expensetracker.ui.driver.DriverFactory;
import com.expensetracker.ui.utils.WaitUtils;
import org.openqa.selenium.By;

public class RegistrationPage {

    private final By firstName = By.id("firstName");
    private final By lastName = By.id("lastName");
    private final By email = By.id("email");
    private final By password = By.id("password");
    private final By submit = By.cssSelector("button[type='submit'], input[type='submit']");

    private final By validationContainer = By.cssSelector("[class*='error'], .alert, .alert-danger, .invalid-feedback");

    public void open() {
        DriverFactory.getDriver().get(TestConfig.getBaseUrl() + "/registration");
    }

    public void register(String fn, String ln, String em, String pw) {
        WaitUtils.visible(firstName).clear();
        WaitUtils.visible(firstName).sendKeys(fn);

        WaitUtils.visible(lastName).clear();
        WaitUtils.visible(lastName).sendKeys(ln);

        WaitUtils.visible(email).clear();
        WaitUtils.visible(email).sendKeys(em);

        WaitUtils.visible(password).clear();
        WaitUtils.visible(password).sendKeys(pw);

        WaitUtils.clickable(submit).click();
    }

    public boolean isValidationMessageContaining(String text) {
        try {
            return WaitUtils.visible(validationContainer).getText().toLowerCase().contains(text.toLowerCase());
        } catch (Exception e) {
            return false;
        }
    }
}
