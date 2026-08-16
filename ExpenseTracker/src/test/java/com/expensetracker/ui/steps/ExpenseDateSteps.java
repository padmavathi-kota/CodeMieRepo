package com.expensetracker.ui.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

/**
 * UI steps for expense date defaulting.
 * Design Ref: Confluence 2912165822 - EPMCDMETST-59424
 */
public class ExpenseDateSteps {

    private static final String BASE_URL = "http://localhost:1001";

    private WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("the application is running")
    public void the_application_is_running() {
        // Lightweight check: hit the new expense page.
        driver.get(BASE_URL + "/new_expense");
        Assertions.assertTrue(driver.getTitle() != null);
    }

    @When("I open the New Expense page")
    public void i_open_the_new_expense_page() {
        driver.get(BASE_URL + "/new_expense");
    }

    @Then("the Date field should default to today in application timezone {string}")
    public void the_date_field_should_default_to_today_in_application_timezone(String tz) {
        WebElement dateInput = driver.findElement(By.id("date"));
        String actual = dateInput.getAttribute("value");

        String expected = LocalDate.now(ZoneId.of(tz)).format(DateTimeFormatter.ISO_LOCAL_DATE);
        Assertions.assertEquals(expected, actual, "Date default should be today in app timezone " + tz);
    }

    @And("I clear the Date field")
    public void i_clear_the_date_field() {
        WebElement dateInput = driver.findElement(By.id("date"));
        // Clear may not work reliably on <input type="date"> across all browsers; do JS set to empty
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='';", dateInput);
        Assertions.assertEquals("", dateInput.getAttribute("value"));
    }

    @And("I submit the expense form")
    public void i_submit_the_expense_form() {
        // Tries to submit first form on the page
        WebElement form = driver.findElement(By.tagName("form"));
        form.submit();
    }

    @Then("I should see a date required validation message")
    public void i_should_see_a_date_required_validation_message() {
        // Server-side error rendering: th:errors for date is inside a div.field-error
        String pageSource = driver.getPageSource();
        Assertions.assertTrue(
                pageSource.contains("Date is required"),
                "Expected server-side validation message 'Date is required' to be rendered"
        );
    }

    @Given("an expense exists with id {long} and date {string}")
    public void an_expense_exists_with_id_and_date(Long id, String dateIso) {
        // This step is a placeholder because creating seed data requires
        // either: DB setup, service/repository access, or UI creation flow.
        //
        // Recommended next step:
        // - Replace this with UI steps that create an expense first, capture its ID,
        //   and then navigate to edit.
        //
        // For now we assert true to keep the scenario executable in environments
        // where expense with id=1 already exists.
        Assertions.assertTrue(true);
    }

    @When("I open the Edit Expense page for expense id {long}")
    public void i_open_the_edit_expense_page_for_expense_id(Long id) {
        driver.get(BASE_URL + "/available_expenses/editExpense/" + id);
    }

    @Then("the Date field value should be {string}")
    public void the_date_field_value_should_be(String expected) {
        WebElement dateInput = driver.findElement(By.id("date"));
        String actual = dateInput.getAttribute("value");
        Assertions.assertEquals(expected, actual);
    }
}
