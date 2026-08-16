package com.expensetracker.ui.steps;

import com.expensetracker.ui.config.TestConfig;
import com.expensetracker.ui.driver.DriverFactory;
import com.expensetracker.ui.pages.LoginPage;
import com.expensetracker.ui.pages.TodoDashboardPage;
import com.expensetracker.ui.utils.WaitUtils;
import io.cucumber.java.en.*;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Step definitions for EPMCDMETST-60027: Add Todo inline validation and accessible error messaging
 */
public class AddTodoValidationSteps {

    private final LoginPage loginPage = new LoginPage();
    private TodoDashboardPage todoDashboardPage;
    private WebDriver driver;
    private WebDriverWait wait;

    private void initializeDriver() {
        if (driver == null) {
            driver = DriverFactory.getDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(TestConfig.getExplicitWaitSeconds()));
        }
    }

    private void initializeTodoPage() {
        initializeDriver();
        if (todoDashboardPage == null) {
            todoDashboardPage = new TodoDashboardPage(driver);
        }
    }

    @Given("I am logged in to ExpenseTracker")
    public void i_am_logged_in_to_expense_tracker() {
        initializeDriver();
        String username = System.getenv("ET_USERNAME");
        String password = System.getenv("ET_PASSWORD");

        if (username == null || password == null) {
            username = "test@example.com";
            password = "password123";
        }

        loginPage.open();
        loginPage.login(username, password);
        WaitUtils.urlContains("/dashboard");
    }

    @Given("I navigate to the Todo dashboard")
    public void i_navigate_to_the_todo_dashboard() {
        initializeTodoPage();
        driver.get(TestConfig.getBaseUrl() + "/todos");
        wait.until(driver -> todoDashboardPage.isLoaded());
    }

    @When("I submit the Add Todo form with an empty task name and no due date")
    public void i_submit_the_add_todo_form_with_an_empty_task_name_and_no_due_date() {
        initializeTodoPage();
        todoDashboardPage.clearTaskName();
        todoDashboardPage.clearDueDate();
        todoDashboardPage.clickAddTodo();
    }

    @When("I submit the Add Todo form with an empty task name and a valid due date")
    public void i_submit_the_add_todo_form_with_an_empty_task_name_and_a_valid_due_date() {
        initializeTodoPage();
        todoDashboardPage.clearTaskName();
        String tomorrow = LocalDate.now().plusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
        todoDashboardPage.setDueDate(tomorrow);
        todoDashboardPage.clickAddTodo();
    }

    @When("I submit the Add Todo form with a valid task name and no due date")
    public void i_submit_the_add_todo_form_with_a_valid_task_name_and_no_due_date() {
        initializeTodoPage();
        todoDashboardPage.typeTaskName("Complete project documentation");
        todoDashboardPage.clearDueDate();
        todoDashboardPage.clickAddTodo();
    }

    @When("I submit the Add Todo form with valid inputs")
    public void i_submit_the_add_todo_form_with_valid_inputs() {
        initializeTodoPage();
        todoDashboardPage.typeTaskName("Valid task name");
        String tomorrow = LocalDate.now().plusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
        todoDashboardPage.setDueDate(tomorrow);
        todoDashboardPage.clickAddTodo();
    }

    @Given("required field errors are displayed on the Add Todo form")
    public void required_field_errors_are_displayed_on_the_add_todo_form() {
        initializeTodoPage();
        todoDashboardPage.clearTaskName();
        todoDashboardPage.clearDueDate();
        todoDashboardPage.clickAddTodo();
        wait.until(driver -> todoDashboardPage.isTaskNameErrorDisplayed());
    }

    @When("I enter a valid task name")
    public void i_enter_a_valid_task_name() {
        initializeTodoPage();
        todoDashboardPage.typeTaskName("Review pull request");
    }

    @When("I select a valid due date")
    public void i_select_a_valid_due_date() {
        initializeTodoPage();
        String tomorrow = LocalDate.now().plusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
        todoDashboardPage.setDueDate(tomorrow);
    }

    @Then("the Add Todo form should not be submitted")
    public void the_add_todo_form_should_not_be_submitted() {
        initializeTodoPage();
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertThat(currentUrl).contains("/todos");
    }

    @Then("I should see the task name required error message")
    public void i_should_see_the_task_name_required_error_message() {
        initializeTodoPage();
        wait.until(driver -> todoDashboardPage.isTaskNameErrorDisplayed());
        Assertions.assertThat(todoDashboardPage.isTaskNameErrorDisplayed()).isTrue();
        Assertions.assertThat(todoDashboardPage.getTaskNameErrorText()).containsIgnoringCase("required");
    }

    @Then("I should see the due date required error message")
    public void i_should_see_the_due_date_required_error_message() {
        initializeTodoPage();
        wait.until(driver -> todoDashboardPage.isDueDateErrorDisplayed());
        Assertions.assertThat(todoDashboardPage.isDueDateErrorDisplayed()).isTrue();
        Assertions.assertThat(todoDashboardPage.getDueDateErrorText()).containsIgnoringCase("required");
    }

    @Then("I should not see the task name required error message")
    public void i_should_not_see_the_task_name_required_error_message() {
        initializeTodoPage();
        Assertions.assertThat(todoDashboardPage.isTaskNameErrorDisplayed()).isFalse();
    }

    @Then("I should not see the due date required error message")
    public void i_should_not_see_the_due_date_required_error_message() {
        initializeTodoPage();
        Assertions.assertThat(todoDashboardPage.isDueDateErrorDisplayed()).isFalse();
    }

    @Then("focus should move to the first invalid field")
    public void focus_should_move_to_the_first_invalid_field() {
        initializeTodoPage();
        WebElement activeElement = todoDashboardPage.getActiveElement();
        Assertions.assertThat(activeElement).isNotNull();
    }

    @Then("focus should move to the task name field")
    public void focus_should_move_to_the_task_name_field() {
        initializeTodoPage();
        WebElement activeElement = todoDashboardPage.getActiveElement();
        WebElement taskNameField = todoDashboardPage.getTaskNameInput();
        Assertions.assertThat(activeElement).isEqualTo(taskNameField);
    }

    @Then("focus should move to the due date field")
    public void focus_should_move_to_the_due_date_field() {
        initializeTodoPage();
        WebElement activeElement = todoDashboardPage.getActiveElement();
        WebElement dueDateField = todoDashboardPage.getDueDateInput();
        Assertions.assertThat(activeElement).isEqualTo(dueDateField);
    }

    @Then("the task name error message should disappear")
    public void the_task_name_error_message_should_disappear() {
        initializeTodoPage();
        wait.until(driver -> !todoDashboardPage.isTaskNameErrorDisplayed());
        Assertions.assertThat(todoDashboardPage.isTaskNameErrorDisplayed()).isFalse();
    }

    @Then("the due date error message should disappear")
    public void the_due_date_error_message_should_disappear() {
        initializeTodoPage();
        wait.until(driver -> !todoDashboardPage.isDueDateErrorDisplayed());
        Assertions.assertThat(todoDashboardPage.isDueDateErrorDisplayed()).isFalse();
    }

    @Then("the todo should be added successfully")
    public void the_todo_should_be_added_successfully() {
        initializeDriver();
        wait.until(ExpectedConditions.or(
            ExpectedConditions.urlContains("/todos"),
            ExpectedConditions.urlContains("/dashboard")
        ));
    }

    @Then("the task name field should have aria-invalid set to true")
    public void the_task_name_field_should_have_aria_invalid_set_to_true() {
        initializeTodoPage();
        String ariaInvalid = todoDashboardPage.getTaskNameAriaInvalid();
        Assertions.assertThat(ariaInvalid).isEqualTo("true");
    }

    @Then("the due date field should have aria-invalid set to true")
    public void the_due_date_field_should_have_aria_invalid_set_to_true() {
        initializeTodoPage();
        String ariaInvalid = todoDashboardPage.getDueDateAriaInvalid();
        Assertions.assertThat(ariaInvalid).isEqualTo("true");
    }

    @Then("the task name field should be associated to its error message via aria-describedby")
    public void the_task_name_field_should_be_associated_to_its_error_message_via_aria_describedby() {
        initializeTodoPage();
        String ariaDescribedBy = todoDashboardPage.getTaskNameAriaDescribedBy();
        Assertions.assertThat(ariaDescribedBy).isNotNull();
        Assertions.assertThat(ariaDescribedBy).containsAnyOf("taskName-error", "error");
    }

    @Then("the due date field should be associated to its error message via aria-describedby")
    public void the_due_date_field_should_be_associated_to_its_error_message_via_aria_describedby() {
        initializeTodoPage();
        String ariaDescribedBy = todoDashboardPage.getDueDateAriaDescribedBy();
        Assertions.assertThat(ariaDescribedBy).isNotNull();
        Assertions.assertThat(ariaDescribedBy).containsAnyOf("dueDate-error", "error");
    }

    @Then("the error summary live region should announce form errors")
    public void the_error_summary_live_region_should_announce_form_errors() {
        initializeTodoPage();
        wait.until(driver -> todoDashboardPage.isErrorSummaryDisplayed());
        Assertions.assertThat(todoDashboardPage.isErrorSummaryDisplayed()).isTrue();
        String summaryText = todoDashboardPage.getErrorSummaryText();
        Assertions.assertThat(summaryText).isNotEmpty();
    }
}
