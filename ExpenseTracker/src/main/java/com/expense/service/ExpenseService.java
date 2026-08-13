package com.expense.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expense.entity.Expense;
import com.expense.entity.user;
import com.expense.repository.ExpenseRepository;
import com.expense.repository.UserRepository;

@Service
public class ExpenseService {
	
	@Autowired
	private ExpenseRepository bRepo;
	@Autowired 
	private UserRepository urepo;
	
	public Expense save(Expense b, String username) {
		user name= urepo.findByEmail(username);
		b.setuser(name);
		return bRepo.save(b);
	}
	
	public List<Expense> getAllExpenses(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();
	    return bRepo.findAllByUserEmail(username);
	}
	
	public Expense getById(int id, String username) {
		//user name= urepo.findByEmail(username);
		return bRepo.findByIdAndUserEmail(id, username);
	}
	@Transactional
	public void deleteExpenseByIdAndUserId(int id, String u) {
		bRepo.deleteByIdAndUserEmail(id, u);
	}
	public List<Expense> getFilteredEntries(Integer month, Integer year) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();
		if (month != 0 && year != null) {
            // Filter entries based on both month and year
            return bRepo.findByMonthAndYear(month, year,username);
        } else if (month != 0) {
            // Filter entries based on month only
        	//int m = Integer.parseInt(month);
            return bRepo.findByMonth(month,username);
        } else if (year != null) {
            // Filter entries based on year only
        	//int y = Integer.parseInt(year); 
            return bRepo.findByYear(year,username);
        } else if (month == 0 && year == null) {
            // Filter entries based on both month and year
        	return bRepo.findAllByUserEmail(username);
        }else {
            // No filters specified, return all entries
            return bRepo.findAllByUserEmail(username);
        }
    }
	 public List<Expense> getExpensesByYear(int year) {
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    String username = authentication.getName();
	        return bRepo.findByYear(year,username);
	    }
	 public Map<Integer, Double> calculateMonthlyExpenseTotals(List<Expense> expenses) {
	        Map<Integer, Double> monthlyTotals = new HashMap<>();

	        for (Expense expense : expenses) {
	        	LocalDate date = expense.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		        int month = date.getMonthValue();
	            double amount = expense.getAmount();

	            monthlyTotals.put(month, monthlyTotals.getOrDefault(month, 0.0) + amount);
	        }

	        return monthlyTotals;
	    }

}
