package com.expense.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.expense.entity.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Integer>  {
	 List<Expense> findAllByUserEmail(@Param("email") String email);
	 void deleteByIdAndUserEmail(int recordId, String userId);
	 Expense findByIdAndUserEmail(int id, String username);
	 @Query("SELECT e FROM Expense e JOIN e.user u WHERE u.email = :email " +
	           "AND (:month = 0 OR MONTH(e.date) = :month) " +
	           "AND (:year IS NULL OR YEAR(e.date) = :year)")
	 List<Expense> findByMonthAndYear(@Param("month")int month, @Param("year")int year,@Param("email") String email);
	 @Query("SELECT e FROM Expense e JOIN e.user u WHERE u.email = :email AND MONTH(e.date) = :month")
	    List<Expense> findByMonth(@Param("month")int month,@Param("email") String email);

	 @Query("SELECT e FROM Expense e JOIN e.user u WHERE u.email = :email AND YEAR(e.date) = :year")
	    List<Expense> findByYear(@Param("year") int year,@Param("email") String email);
	



}
