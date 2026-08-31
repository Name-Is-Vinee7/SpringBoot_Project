package com.example.expenseTracker.service;

import com.example.expenseTracker.entity.ExpenseTrackerEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface ETServiceImp {

    public ExpenseTrackerEntity saveExpense( ExpenseTrackerEntity expenseTrackerEntity);
}
