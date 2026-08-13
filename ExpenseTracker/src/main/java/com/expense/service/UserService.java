package com.expense.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.expense.entity.user;
import com.expense.login.dto.UserRegistrationDto;
import com.expense.repository.ExpenseRepository;


public interface UserService extends UserDetailsService{
	user save(UserRegistrationDto u);
}
