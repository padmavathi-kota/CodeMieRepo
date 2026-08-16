package com.expensetracker.ui.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TodoDashboardPage {

    private final WebDriver driver;

    public TodoDashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "form#addTodoForm, form#todo-form")
    private WebElement addTodoForm;

    @FindBy(id = "taskName")
    private WebElement taskNameInput;

    @FindBy(id = "dueDate")
    private WebElement dueDateInput;

    @FindBy(css = "#addTodoBtn, form#addTodoForm button[type='submit'], form#todo-form button[type='submit']")
    private WebElement addTodoButton;

    @FindBy(id = "taskName-error")
    private WebElement taskNameErrorById;

    @FindBy(id = "dueDate-error")
    private WebElement dueDateErrorById;

    @FindBy(css = ".field-error")
    private List<WebElement> allFieldErrors;

    @FindBy(id = "todoFormErrorSummary")
    private WebElement todoFormErrorSummary;

    public boolean isLoaded() {
        try {
            return addTodoForm.isDisplayed() && taskNameInput.isDisplayed() && dueDateInput.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clearTaskName() {
        taskNameInput.clear();
    }

    public void typeTaskName(String taskName) {
        taskNameInput.clear();
        taskNameInput.sendKeys(taskName);
    }

    public void clearDueDate() {
        dueDateInput.clear();
    }

    public void setDueDate(String yyyyMmDd) {
        dueDateInput.clear();
        dueDateInput.sendKeys(yyyyMmDd);
    }

    public void clickAddTodo() {
        addTodoButton.click();
    }

    public WebElement getTaskNameInput() {
        return taskNameInput;
    }

    public WebElement getDueDateInput() {
        return dueDateInput;
    }

    public WebElement getActiveElement() {
        return driver.switchTo().activeElement();
    }

    public boolean isTaskNameErrorDisplayed() {
        return isElementDisplayed(taskNameErrorById) || isAnyFieldErrorContainingText("Task name is required");
    }

    public boolean isDueDateErrorDisplayed() {
        return isElementDisplayed(dueDateErrorById) || isAnyFieldErrorContainingText("Due date is required");
    }

    public String getTaskNameErrorText() {
        if (isElementDisplayed(taskNameErrorById)) return safeText(taskNameErrorById);
        return findFieldErrorText("Task name is required");
    }

    public String getDueDateErrorText() {
        if (isElementDisplayed(dueDateErrorById)) return safeText(dueDateErrorById);
        return findFieldErrorText("Due date is required");
    }

    public boolean isErrorSummaryDisplayed() {
        return isElementDisplayed(todoFormErrorSummary);
    }

    public String getErrorSummaryText() {
        return safeText(todoFormErrorSummary);
    }

    public String getTaskNameAriaInvalid() {
        return taskNameInput.getAttribute("aria-invalid");
    }

    public String getDueDateAriaInvalid() {
        return dueDateInput.getAttribute("aria-invalid");
    }

    public String getTaskNameAriaDescribedBy() {
        return taskNameInput.getAttribute("aria-describedby");
    }

    public String getDueDateAriaDescribedBy() {
        return dueDateInput.getAttribute("aria-describedby");
    }

    public String getTaskNameCssClass() {
        return taskNameInput.getAttribute("class");
    }

    public String getDueDateCssClass() {
        return dueDateInput.getAttribute("class");
    }

    private boolean isElementDisplayed(WebElement el) {
        try {
            return el != null && el.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    private String safeText(WebElement el) {
        try {
            return el.getText() == null ? "" : el.getText().trim();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return "";
        }
    }

    private boolean isAnyFieldErrorContainingText(String expected) {
        try {
            for (WebElement e : allFieldErrors) {
                if (e.isDisplayed() && safeText(e).contains(expected)) {
                    return true;
                }
            }
        } catch (Exception ignored) {
        }
        return false;
    }

    private String findFieldErrorText(String expected) {
        try {
            for (WebElement e : allFieldErrors) {
                String t = safeText(e);
                if (!t.isEmpty() && t.contains(expected)) {
                    return t;
                }
            }
        } catch (Exception ignored) {
        }
        return "";
    }
}
