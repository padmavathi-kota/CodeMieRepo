package com.expensetracker.ui.pages;

import com.expensetracker.ui.config.TestConfig;
import com.expensetracker.ui.driver.DriverFactory;
import com.expensetracker.ui.utils.WaitUtils;
import org.openqa.selenium.By;

public class AddExpensePage {

    private final By amount = By.id("amount");
    private final By currency = By.id("currency");
    private final By date = By.id("date");
    private final By category = By.id("category");
    private final By description = By.id("description");
    private final By submit = By.cssSelector("button[type='submit'], input[type='submit']");

    public void open() {
        DriverFactory.getDriver().get(TestConfig.getBaseUrl() + "/new_expense");
    }

    public String readDateValue() {
        return WaitUtils.visible(date).getAttribute("value");
    }

    public void setDate(String yyyyMmDd) {
        WaitUtils.visible(date).clear();
        WaitUtils.visible(date).sendKeys(yyyyMmDd);
    }

    public void createExpense(String amt, String curr, String yyyyMmDdOrNull, String cat, String desc) {
        WaitUtils.visible(amount).clear();
        WaitUtils.visible(amount).sendKeys(amt);

        WaitUtils.visible(currency).clear();
        WaitUtils.visible(currency).sendKeys(curr);

        if (yyyyMmDdOrNull != null) {
            setDate(yyyyMmDdOrNull);
        }

        WaitUtils.visible(category).clear();
        WaitUtils.visible(category).sendKeys(cat);

        WaitUtils.visible(description).clear();
        WaitUtils.visible(description).sendKeys(desc);

        WaitUtils.clickable(submit).click();
    }
}
