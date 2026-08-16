#!/bin/bash
# Test Execution Script for EPMCDMETST-60228
# Default Expense Date to Today in Add/Edit Form

echo "========================================="
echo "EPMCDMETST-60228 Test Execution"
echo "========================================="
echo ""
echo "Test Scope: Default expense date functionality"
echo "Feature File: src/test/resources/features/EPMCDMETST-60228_DefaultExpenseDate.feature"
echo "Test Runner: EPMCDMETST60228TestRunner"
echo ""

# Set up test environment
export MAVEN_OPTS="-Xmx1024m"

# Navigate to project directory
cd "$(dirname "$0")"

echo "Step 1: Clean previous test artifacts..."
mvn clean

echo ""
echo "Step 2: Compile test sources..."
mvn test-compile

echo ""
echo "Step 3: Execute Cucumber tests for EPMCDMETST-60228..."
mvn test -Dtest=EPMCDMETST60228TestRunner

echo ""
echo "========================================="
echo "Test Execution Complete"
echo "========================================="
echo ""
echo "Test Reports Generated:"
echo "  - HTML Report: target/cucumber-reports/EPMCDMETST-60228.html"
echo "  - JSON Report: target/cucumber-reports/EPMCDMETST-60228.json"
echo "  - JUnit XML: target/cucumber-reports/EPMCDMETST-60228.xml"
echo ""
