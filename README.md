# Expense Tracker Web Application
An intuitive web-based application for tracking and managing personal expenses, developed using the MVC architecture in Spring Boot. 

## Objective
The main goal of this project is to create a web-based expense tracker that enables users to easily manage and track their day-to-day expenses. The application provides a user-friendly interface for expense creation, updation, tracking, categorization, and detailed reporting.

## Features Implemented
- **User-specific Login**: Enables users to have their personal profile with their expenses. The profile is secured using login and register function.
- **Authentication**: Authentication is provided using spring security. The user is allowed to create a new account and track the expense. The password will be stored in the database in encrypted format. An option is provided to logout of his account in every page.
- **User-friendly Dashboard**: Enables users to get an overview of their expenses at a glance.
- **Expense Tracking**: Seamlessly add, edit, or remove expenses.
- **Categorization**: Classify your expenses into various categories for better finance management.
- **Detailed Reporting**: Generate detailed reports to understand and analyze your spending habits.

## Technologies Used
- **Backend**: Java, Maven, Spring Boot 1.5.1, Spring Data JPA, Spring Security
- **Frontend**: HTML, CSS, Thymeleaf, chart.js.
- **Database**: Currently the application is using Oracle database, all the credentails can be changed in application properties. No need to create database or tables, everything will be created for you, all you have to do is change the credentails.
- **Testing**: JUnit.
- **IDE**: Visual Studio Code.
- **Server**: tomcat 8.0 or higher versions.

## Installation & Setup
### Prerequisites:
- Java JDK
- Maven 
- MySQL Server
- Tomcat Server
- VS COde IDE
### Steps:
1. Clone the Repository
```
git clone [URL_TO_GIT_REPOSITORY]
```
2. Go to the project directory
```
cd ExpenseTracker
```
3. Create Database
```
CREATE DATABASE ExpenseTracker
```
4. Start the Application
- Open the project in VS Code.
- Right-click on the project > Run As > Spring Boot App.
- The server will start on default port 1001.
or use the below command directly
```
  mvn spring-boot:run
```
5. Access the Web App
Open your web browser and navigate to http://localhost:1001.

## Project Screenshots
<p align="center"><img width="1429" alt="Screenshot 2023-09-11 at 1 35 35 PM" src="https://github.com/Namita-Namita/Expense_Tracker/assets/31967922/fb478d08-ae40-4115-af8b-82879f39282a">
<br><em>Fig 1.: Snapshot of Welcome page (Dashboard)</em></p>
<p align="center"><img width="1432" alt="Screenshot 2023-09-11 at 1 36 42 PM" src="https://github.com/Namita-Namita/Expense_Tracker/assets/31967922/2fe24673-8182-44f3-b24a-6c20c97fedbd"><br><em>Fig 2.: Snapshot of New User Register page (Login)</em></p>
<p align="center"><img width="1437" alt="Screenshot 2023-09-11 at 1 37 17 PM" src="https://github.com/Namita-Namita/Expense_Tracker/assets/31967922/4d2d6f67-019a-427a-99bb-a857ea11253a"><br><em>Fig 3.: Snapshot of New User Registration successful</em></p>
<p align="center"><img width="1428" alt="Screenshot 2023-09-11 at 1 38 07 PM" src="https://github.com/Namita-Namita/Expense_Tracker/assets/31967922/f9df4952-abfd-43b1-8d60-dd57e2949812"><br><em>Fig 4.: Snapshot of Login page (Login)</em></p>
<p align="center"><img width="1432" alt="Screenshot 2023-09-11 at 1 38 43 PM" src="https://github.com/Namita-Namita/Expense_Tracker/assets/31967922/f1a29657-e8eb-4873-be6b-b7abab4b7993"><br><em>Fig 5.: Snapshot after login successful</em></p>
<p align="center"><img width="1433" alt="Screenshot 2023-09-11 at 1 40 22 PM" src="https://github.com/Namita-Namita/Expense_Tracker/assets/31967922/5de1dbc2-9f1e-408a-99ce-22322ca7efd1"><br><em>Fig 6.: Snapshot of Add New Expense page</em></p>
<p align="center"><img width="1431" alt="Screenshot 2023-09-11 at 1 42 25 PM" src="https://github.com/Namita-Namita/Expense_Tracker/assets/31967922/4f04c808-dc1e-41fa-b82d-e8596228f696"><br><em>Fig 7.: Snapshot of All Expenses page, all the expenses with an option to edit or update is displayed. Filter by month or year is available.</em></p>
<p align="center"><img width="1440" alt="Screenshot 2023-09-11 at 1 43 55 PM" src="https://github.com/Namita-Namita/Expense_Tracker/assets/31967922/027197ed-4487-4cf4-a840-74574f83e764"><br><em>Fig 8.: Snapshot of Expense Report page, all the expenses on yearly basis can be displayed in graphical format.</em></p>

## Future Enhancements:
* **Custom categories:** Give users the ability to create their own categories, beyond the predefined ones.
* **Custom currencies:** Give users the ability to create their own currencies, beyond the predefined ones.
* **Export and Import data:** Allow users to export their data for backup or analysis in other software and import data from other sources.
* **Monthly/Weekly Budget Caps:** Allow users to set a budget cap for specific durations.
* **Budget notifications:** Notify users when they are close to reaching or have exceeded their budget.
