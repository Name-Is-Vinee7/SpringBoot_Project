package com.example.expenseTracker.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                                       //lombok will crete getters and setters for all the fields
@AllArgsConstructor                         //Constructor with arguments
@NoArgsConstructor                          //Constructor without arguments

public class ExpenseTrackerBean {

    private String expenseName;
    private double expenseAmount;
    private String expenseDate;

}

