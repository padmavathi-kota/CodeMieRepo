package com.expensetracker.ui.steps;

import com.expensetracker.ui.pages.ExpensesListPage;
import com.expensetracker.ui.pages.LoginPage;
import com.expensetracker.ui.utils.WaitUtils;
import io.cucumber.java.en.*;
import org.assertj.core.api.Assertions;

public class SecuritySteps {

    private final ExpensesListPage expensesListPage = new ExpensesListPage();
    private final LoginPage loginPage = new LoginPage();

    @Given("I am not logged in")
    public void i_am_not_logged_in() {
        // No-op: each scenario starts with a fresh browser instance by default
    }

    @When("I open the Expenses List page")
    public void i_open_the_expenses_list_page() {
        expensesListPage.open();
    }

    @Then("I should be redirected to the Login page")
    public void i_should_be_redirected_to_the_login_page() {
        WaitUtils.urlContains("/login");
        Assertions.assertThat(loginPage.isAt()).isTrue();
    }
}
