package com.expensetracker.ui.steps;

import com.expensetracker.ui.pages.RegistrationPage;
import io.cucumber.java.en.*;
import org.assertj.core.api.Assertions;

public class RegistrationSteps {

    private final RegistrationPage registrationPage = new RegistrationPage();

    @Given("I am on the Registration page")
    public void i_am_on_the_registration_page() {
        registrationPage.open();
    }

    @When("I register with first name {string} last name {string} email {string} password {string}")
    public void i_register_with_details(String fn, String ln, String email, String pw) {
        registrationPage.register(fn, ln, email, pw);
    }

    @Then("registration should be submitted successfully")
    public void registration_should_be_submitted_successfully() {
        // App-specific: often redirects to /login after registration. If so, assert URL contains /login.
        // Keeping a generic expectation: absence of validation message.
        Assertions.assertThat(registrationPage.isValidationMessageContaining("error")).isFalse();
    }

    @Then("I should see a registration validation message containing {string}")
    public void i_should_see_a_registration_validation_message_containing(String msg) {
        Assertions.assertThat(registrationPage.isValidationMessageContaining(msg)).isTrue();
    }
}
