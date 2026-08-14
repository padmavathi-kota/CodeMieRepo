package com.expensetracker.ui.steps;

import com.expensetracker.ui.config.TestConfig;
import com.expensetracker.ui.driver.DriverFactory;
import com.expensetracker.ui.pages.DashboardPage;
import com.expensetracker.ui.pages.ExpensesListPage;
import com.expensetracker.ui.pages.LoginPage;
import com.expensetracker.ui.utils.WaitUtils;
import io.cucumber.java.en.*;
import org.assertj.core.api.Assertions;

public class AuthSteps {

    private final LoginPage loginPage = new LoginPage();
    private final DashboardPage dashboardPage = new DashboardPage();
    private final ExpensesListPage expensesListPage = new ExpensesListPage();

    @Given("application base url is configured")
    public void application_base_url_is_configured() {
        Assertions.assertThat(TestConfig.getBaseUrl()).isNotBlank();
    }

    @Given("I am on the Login page")
    public void i_am_on_the_login_page() {
        loginPage.open();
        Assertions.assertThat(loginPage.isAt()).isTrue();
    }

    @When("I login with username {string} and password {string}")
    public void i_login_with_username_and_password(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("I should be redirected to the Dashboard page")
    public void i_should_be_redirected_to_the_dashboard_page() {
        WaitUtils.urlContains("/dashboard");
        Assertions.assertThat(dashboardPage.isAt()).isTrue();
    }

    @Then("I should see an authentication error message")
    public void i_should_see_an_authentication_error_message() {
        Assertions.assertThat(loginPage.isErrorVisible()).isTrue();
    }

    @Given("I am logged in as username {string} with password {string}")
    public void i_am_logged_in_as_username_with_password(String username, String password) {
        loginPage.open();
        loginPage.login(username, password);
        WaitUtils.urlContains("/dashboard");
    }

    @When("I logout")
    public void i_logout() {
        DriverFactory.getDriver().get(TestConfig.getBaseUrl() + "/logout");
    }

    @Then("I should be redirected to the Login page")
    public void i_should_be_redirected_to_the_login_page() {
        WaitUtils.urlContains("/login");
        Assertions.assertThat(loginPage.isAt()).isTrue();
    }

    @Then("I should not be able to access the Expenses List page without logging in")
    public void i_should_not_be_able_to_access_the_expenses_list_page_without_logging_in() {
        expensesListPage.open();
        WaitUtils.urlContains("/login");
        Assertions.assertThat(loginPage.isAt()).isTrue();
    }
}
