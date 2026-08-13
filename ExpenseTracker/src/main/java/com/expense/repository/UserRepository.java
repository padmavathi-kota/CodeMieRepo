package com.expense.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expense.entity.user;

@Repository
public interface UserRepository extends JpaRepository<user,Long>  {

	user findByEmail(String email);

}
