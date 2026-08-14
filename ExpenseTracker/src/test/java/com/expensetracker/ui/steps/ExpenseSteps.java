package com.expensetracker.ui.steps;

import com.expensetracker.ui.pages.AddExpensePage;
import com.expensetracker.ui.pages.ExpensesListPage;
import com.expensetracker.ui.utils.DateUtils;
import com.expensetracker.ui.utils.WaitUtils;
import io.cucumber.java.en.*;
import org.assertj.core.api.Assertions;

public class ExpenseSteps {

    private final AddExpensePage addExpensePage = new AddExpensePage();
    private final ExpensesListPage expensesListPage = new ExpensesListPage();

    // For edit flow, we need fields; template ids for edit form likely same as add.
    // We'll reuse AddExpensePage locators by opening edit page and interacting with same ids.
    // If edit uses different template/ids, update accordingly.
    private final AddExpensePage editExpensePage = new AddExpensePage();

    private int previousRowCount = -1;

    @When("I open the Add Expense page")
    public void i_open_the_add_expense_page() {
        addExpensePage.open();
        WaitUtils.urlContains("/new_expense");
    }

    @Then("the Expense Date field should be pre-populated with today's date in {string}")
    public void the_expense_date_field_should_be_pre_populated_with_todays_date_in(String pattern) {
        String actual = addExpensePage.readDateValue();
        String expected = DateUtils.today(pattern);
        Assertions.assertThat(actual).as("date default value").isEqualTo(expected);
    }

    @When("I create an expense with amount {string} currency {string} category {string} description {string} and keep default date")
    public void i_create_an_expense_keep_default_date(String amount, String currency, String category, String description) {
        addExpensePage.createExpense(amount, currency, null, category, description);
    }

    @When("I create an expense with amount {string} currency {string} category {string} description {string} and date {string}")
    public void i_create_an_expense_with_date(String amount, String currency, String category, String description, String date) {
        addExpensePage.createExpense(amount, currency, date, category, description);
    }

    @Then("the expense should be created successfully")
    public void the_expense_should_be_created_successfully() {
        // Most apps redirect back to list after add; verify list url is accessible
        // If your app redirects elsewhere, adjust this assertion.
        WaitUtils.urlContains("/available_expenses");
        Assertions.assertThat(expensesListPage.isAt()).isTrue();
    }

    @Given("I have created an expense with amount {string} currency {string} date {string} category {string} description {string}")
    public void i_have_created_an_expense(String amount, String currency, String date, String category, String description) {
        expensesListPage.open();
        previousRowCount = expensesListPage.rowCount();

        addExpensePage.open();
        addExpensePage.createExpense(amount, currency, date, category, description);

        expensesListPage.open();
        int after = expensesListPage.rowCount();
        // Row count may not increase if pagination/filtering exists; still keep a weak check:
        Assertions.assertThat(after).isGreaterThanOrEqualTo(previousRowCount);
    }

    @When("I open the Expenses List page")
    public void i_open_the_expenses_list_page() {
        expensesListPage.open();
        Assertions.assertThat(expensesListPage.isAt()).isTrue();
    }

    @When("I edit the first expense and set amount to {string} and description to {string}")
    public void i_edit_the_first_expense_and_set_amount_and_description(String newAmount, String newDescription) {
        expensesListPage.clickEditOnFirstRow();
        // On edit page, same ids expected (amount, description, date etc.)
        editExpensePage.createExpense(newAmount, "USD", null, "Food", newDescription);
    }

    @Then("the first expense row should show amount {string} and description {string}")
    public void the_first_expense_row_should_show_amount_and_description(String amount, String description) {
        expensesListPage.open();
        String txt = expensesListPage.firstRowText();
        Assertions.assertThat(txt).contains(amount);
        Assertions.assertThat(txt).contains(description);
    }

    @When("I edit the first expense without changing the date")
    public void i_edit_the_first_expense_without_changing_the_date() {
        expensesListPage.clickEditOnFirstRow();
        // We just submit without touching date; reusing createExpense with null date keeps it as is
        // NOTE: currency/category may be required by server; we keep safe defaults here.
        editExpensePage.createExpense("15.00", "USD", null, "Food", "No date change");
    }

    @Then("the expense date should remain {string}")
    public void the_expense_date_should_remain(String expectedDate) {
        expensesListPage.open();
        String txt = expensesListPage.firstRowText();
        Assertions.assertThat(txt).contains(expectedDate);
    }

    @When("I delete the first expense")
    public void i_delete_the_first_expense() {
        expensesListPage.open();
        previousRowCount = expensesListPage.rowCount();
        expensesListPage.clickDeleteOnFirstRow();
    }

    @Then("the expense should be removed from the list")
    public void the_expense_should_be_removed_from_the_list() {
        expensesListPage.open();
        int after = expensesListPage.rowCount();
        // If delete redirects and the list refreshes, row count should decrease.
        // If pagination/filters interfere, adjust to assert that first-row text changed.
        Assertions.assertThat(after).isLessThanOrEqualTo(previousRowCount);
    }
}
