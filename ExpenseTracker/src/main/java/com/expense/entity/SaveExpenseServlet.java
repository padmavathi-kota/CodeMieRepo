package com.expense.entity;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.*;
import java.sql.*;

@WebServlet(name = "SaveExpenseServlet", urlPatterns = "/saveExpense")
public class SaveExpenseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve the form data
            String amount = request.getParameter("amount");
            String date = request.getParameter("date");
            String category = request.getParameter("category");
            String description = request.getParameter("description");

            // Establish the database connection
            Connection connection = DatabaseConnection.getConnection();

            // Prepare the SQL statement
            String sql = "INSERT INTO expenses (amount, date, category, description) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, amount);
            statement.setString(2, date);
            statement.setString(3, category);
            statement.setString(4, description);

            // Execute the SQL statement
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                response.getWriter().write("Expense saved successfully!");
            } else {
                response.getWriter().write("Failed to save the expense.");
            }
            // Close the database connection
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("An error occurred while saving the expense.");
        }
    }
}
