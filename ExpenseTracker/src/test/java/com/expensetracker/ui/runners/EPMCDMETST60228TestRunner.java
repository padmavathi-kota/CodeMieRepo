package com.expensetracker.ui.runners;

import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.*;

/**
 * Test runner for EPMCDMETST-60228: Default expense date to today in Add/Edit form
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(
    key = "cucumber.filter.tags", 
    value = "@Jira-EPMCDMETST-60228"
)
@ConfigurationParameter(
    key = PLUGIN_PROPERTY_NAME, 
    value = "pretty, html:target/cucumber-reports/EPMCDMETST-60228.html, json:target/cucumber-reports/EPMCDMETST-60228.json"
)
@ConfigurationParameter(
    key = GLUE_PROPERTY_NAME, 
    value = "com.expensetracker.ui.steps"
)
public class EPMCDMETST60228TestRunner {
}
