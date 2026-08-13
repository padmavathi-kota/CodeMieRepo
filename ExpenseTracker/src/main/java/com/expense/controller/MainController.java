package com.expense.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.expense.entity.user;
import com.expense.repository.UserRepository;
import com.expense.entity.Expense;
import com.expense.service.ExpenseService;

import java.security.Principal;
//import com.expense.service.MyBookListService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
//@RequestMapping("/userlogin")
public class MainController {
	
	@Autowired
	UserRepository repo;
	
	@GetMapping("/login")
	public String login( ){//Model model) {
//		user u= new user();
//		model.addAttribute("user",u);
		return "Login";
	}
	@GetMapping("/dashboard")
	public String dashboard( Model model, Principal p){//Model model) {
		String username= p.getName();
		String[] s= username.split("@");
		model.addAttribute("message", "Welcome, " + s[0] + "!");
		return "dashboard";
	}
//	@PostMapping("/userlogin")
//	public String Loginhome(@ModelAttribute ("user") user u) {
//		String email= u.getEmail();
//		Optional<user> data= Optional.of(repo.findByEmail("email"));
//		if(u.getPassword().equals(data.get().getPassword()))
//		return "home";
//		else
//			return "redirect:/login?failure";
//	}
	
	
	
}
