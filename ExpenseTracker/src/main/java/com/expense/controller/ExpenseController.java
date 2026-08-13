package com.expense.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.expense.entity.Expense;
import com.expense.service.ExpenseService;

import java.security.Principal;
//import com.expense.service.MyBookListService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class ExpenseController {
	
	@Autowired
	private ExpenseService service;
	
	
	//private MyBookListService myBookService;
	@GetMapping("/")
	public String homedef() {
		return "home";
	}
	@GetMapping("/getchart")
	public String chart() {
		return "chart";
	}
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
	@GetMapping("/new_expense")
	public String Newexpense(Model model) {
		String message = (String) model.getAttribute("message");
        model.addAttribute("message", message);
		return "ExpenseForm";
	}
	
	@GetMapping("/available_expenses")
	public ModelAndView getAllExpenses(Model model) {
		List<Expense>list=service.getAllExpenses();
		String message = (String) model.getAttribute("message");
        model.addAttribute("message", message);
		//System.out.println(list);
//		ModelAndView m=new ModelAndView();
//		m.setViewName("bookList");
//		m.addObject("book",list);
		return new ModelAndView("ExpenseList","expense",list);
	}
	
	@PostMapping("/save")
	public String addExpense(@ModelAttribute Expense b, Principal p) {
		String email= p.getName();
		service.save(b,email);
		return "redirect:/available_expenses";
	}
//	@GetMapping("/my_books")
//	public String getMyBooks(Model model)
//	{
//		List<MyBookList>list=myBookService.getAllMyBooks();
//		model.addAttribute("book",list);
//		return "myBooks";
//	}
//	@RequestMapping("/mylist/{id}")
//	public String getMyList(@PathVariable("id") int id) {
//		Expense b=service.getBookById(id);
//		MyBookList mb=new MyBookList(b.getId(),b.getAmount(),b.getCurrency(),b.getDate());
//		myBookService.saveMyBooks(mb);
//		return "redirect:/my_books";
//	}
	
	@RequestMapping("/available_expenses/editExpense/{id}")
	public String editExpense(@PathVariable("id") int id, Model model, Principal p) {
		String email= p.getName();
		Expense expense=service.getById(id,email);
		model.addAttribute("expense",expense);
		
		    
		return "ExpenseFormEdit";
	}
	@RequestMapping("/deleteExpense/{id}")
	public String deleteExpense(@PathVariable("id")int id, Principal p) {
		String email= p.getName();
		service.deleteExpenseByIdAndUserId(id, email);
		return "redirect:/available_expenses";
	}
	@RequestMapping(value="/available_expenses/editExpense/update/{id}", method= {RequestMethod.GET, RequestMethod.PUT})
	public String updateExpense(@ModelAttribute Expense expense, Principal p) {
		String email= p.getName();
		service.save(expense, email);
		return "redirect:/available_expenses";
	}
	@GetMapping("/entries")
    public ModelAndView getFilteredEntries(@RequestParam(required = false)  Integer month, @RequestParam(required = false)  Integer year, Principal p, Model model) {
		String email= p.getName();
		List<Expense> filteredEntries = service.getFilteredEntries(month, year);
	    model.addAttribute("entries", filteredEntries);
	    return new ModelAndView("ExpenseList","expense",filteredEntries);
    }
	@GetMapping("/chart")
	public String generateExpenseChart(@RequestParam("year") int year, Model model) {
		        List<Expense> expenses = service.getExpensesByYear(year);
		        Map<Integer, Double> monthlyTotals = service.calculateMonthlyExpenseTotals(expenses);

		        model.addAttribute("year", year);
		        model.addAttribute("monthlyTotals", monthlyTotals);

	    return "ExpenseChart"; // Return the view name for the chart
	}

}
