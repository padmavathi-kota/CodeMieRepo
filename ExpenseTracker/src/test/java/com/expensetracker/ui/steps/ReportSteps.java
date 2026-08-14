package com.expensetracker.ui.steps;

import com.expensetracker.ui.pages.ReportsPage;
import io.cucumber.java.en.*;
import org.assertj.core.api.Assertions;

public class ReportSteps {

    private final ReportsPage reportsPage = new ReportsPage();

    @When("I open the Chart page")
    public void i_open_the_chart_page() {
        reportsPage.openChart();
    }

    @When("I open the GetChart page")
    public void i_open_the_getchart_page() {
        reportsPage.openGetChart();
    }

    @Then("the chart/report page should be displayed")
    public void the_chart_report_page_should_be_displayed() {
        Assertions.assertThat(reportsPage.isAtReportPage()).isTrue();
    }
}
