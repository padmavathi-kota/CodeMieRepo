package com.expensetracker.ui.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

/**
 * Enhanced UI steps for expense date defaulting - EPMCDMETST-60228
 * Extends coverage for default date behavior in Add/Edit forms
 */
public class ExpenseDateEnhancedSteps {

    private static final String BASE_URL = "http://localhost:1001";
    private WebDriver driver;
    private String originalDateValue;

    @Before(order = 1)
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

    @After(order = 1)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Then("the Date field should be editable")
    public void the_date_field_should_be_editable() {
        WebElement dateInput = driver.findElement(By.id("date"));
        Assertions.assertTrue(dateInput.isEnabled(), "Date field should be enabled and editable");
        Assertions.assertFalse(dateInput.getAttribute("readonly") != null && 
                               dateInput.getAttribute("readonly").equals("true"),
                               "Date field should not be readonly");
    }

    @When("I change the Date field to {string}")
    public void i_change_the_date_field_to(String newDate) {
        WebElement dateInput = driver.findElement(By.id("date"));
        originalDateValue = dateInput.getAttribute("value");
        
        // Clear and set new value using JavaScript for reliability
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value=arguments[1];", dateInput, newDate);
        
        // Verify the change was applied
        String actualValue = dateInput.getAttribute("value");
        Assertions.assertEquals(newDate, actualValue, 
            "Date field should be updated to: " + newDate);
    }

    @When("I fill Amount with {string}")
    public void i_fill_amount_with(String amount) {
        WebElement amountInput = driver.findElement(By.id("amount"));
        amountInput.clear();
        amountInput.sendKeys(amount);
    }

    @When("I fill Description with {string}")
    public void i_fill_description_with(String description) {
        WebElement descInput = driver.findElement(By.id("description"));
        descInput.clear();
        descInput.sendKeys(description);
    }

    @When("I select Category {string}")
    public void i_select_category(String category) {
        WebElement categorySelect = driver.findElement(By.id("category"));
        Select select = new Select(categorySelect);
        select.selectByVisibleText(category);
    }

    @Then("the expense should be saved successfully")
    public void the_expense_should_be_saved_successfully() {
        // Wait for redirect or success message
        try {
            // Check if redirected to expenses list
            String currentUrl = driver.getCurrentUrl();
            boolean redirected = currentUrl.contains("available_expenses") || 
                                currentUrl.contains("expenses");
            
            if (redirected) {
                Assertions.assertTrue(true, "Successfully redirected after save");
            } else {
                // Check for success message
                WebElement successMsg = driver.findElement(
                    By.xpath("//*[contains(@class, 'success') or contains(@class, 'alert-success')]")
                );
                Assertions.assertTrue(successMsg.isDisplayed(), 
                    "Success message should be displayed");
            }
        } catch (Exception e) {
            Assertions.fail("Expected successful save but got: " + e.getMessage());
        }
    }

    @Then("the Date field should not be today's date")
    public void the_date_field_should_not_be_todays_date() {
        WebElement dateInput = driver.findElement(By.id("date"));
        String actualDate = dateInput.getAttribute("value");
        String todayDate = LocalDate.now(ZoneId.of("UTC"))
            .format(DateTimeFormatter.ISO_LOCAL_DATE);
        
        Assertions.assertNotEquals(todayDate, actualDate,
            "Edit form should preserve existing date, not override to today");
    }
}
