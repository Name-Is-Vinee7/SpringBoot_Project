package com.example.expenseTracker.repository;

import com.example.expenseTracker.entity.ExpenseTrackerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseTrackerRepository extends JpaRepository<ExpenseTrackerEntity, Integer> {


}
