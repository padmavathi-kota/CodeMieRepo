package com.expensetracker.ui.pages;

import com.expensetracker.ui.config.TestConfig;
import com.expensetracker.ui.driver.DriverFactory;
import com.expensetracker.ui.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ExpensesListPage {

    private final By month = By.id("month");
    private final By year = By.id("year");

    // We do not know exact table ids; using generic table row selection.
    private final By rows = By.cssSelector("table tbody tr");

    public void open() {
        DriverFactory.getDriver().get(TestConfig.getBaseUrl() + "/available_expenses");
    }

    public boolean isAt() {
        return DriverFactory.getDriver().getCurrentUrl().contains("/available_expenses");
    }

    public void filterBy(String monthValue, String yearValue) {
        WaitUtils.visible(month).clear();
        WaitUtils.visible(month).sendKeys(monthValue);

        WaitUtils.visible(year).clear();
        WaitUtils.visible(year).sendKeys(yearValue);

        // Many apps auto-submit on change; if there is a filter button in template, add it here.
        // Otherwise, trigger blur to let onchange handlers run:
        WaitUtils.visible(year).sendKeys("\t");
    }

    public int rowCount() {
        List<WebElement> list = DriverFactory.getDriver().findElements(rows);
        return list.size();
    }

    public WebElement firstRow() {
        return WaitUtils.visible(rows);
    }

    public void clickEditOnFirstRow() {
        WebElement row = firstRow();
        row.findElement(By.cssSelector("a[href*='/available_expenses/editExpense/']")).click();
    }

    public void clickDeleteOnFirstRow() {
        WebElement row = firstRow();
        row.findElement(By.cssSelector("a[href*='/deleteExpense/']")).click();
    }

    public String firstRowText() {
        return firstRow().getText();
    }
}
