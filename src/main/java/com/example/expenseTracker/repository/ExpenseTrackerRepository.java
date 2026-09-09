package com.example.expenseTracker.repository;

import com.example.expenseTracker.entity.ExpenseTrackerEntity;
import com.example.expenseTracker.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseTrackerRepository extends JpaRepository<ExpenseTrackerEntity, Integer> {

    List<ExpenseTrackerEntity> findByUserExpenseId(UserEntity userExpenseId);
    Optional<ExpenseTrackerEntity> findByExpenseId(Integer id);
    void deleteById(Integer id);
}
