package com.expense.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Expense {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String category;
    private String currency;
    private double amount;
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private user user;

    // Constructors, getters, and setters

    
	public Expense(int id, Date date, String category, String currency, double amount, String description, user user) {
		super();
		this.id = id;
		this.date = date;
		this.category = category;
		this.currency = currency;
		this.amount = amount;
		this.description = description;
		this.user=user;
	}
	public Expense() {
		super();
		// TODO Auto-generated constructor stub
	}
	public user getuser() {
        return user;
    }

    public void setuser(user user) {
        this.user = user;
    }
	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Date getDate() {
    	return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }
    public String getCurrency() {
        return currency;
    }
    public String getDescription() {
        return description;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
	
}
