package com.example.expenseTracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.processing.Pattern;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.NumberFormat;

import java.util.Date;
@Entity
@Table(name = "expense_tracker")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseTrackerEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer expenseId;
    @NonNull
    private String category;
    @NonNull
    private String description;
    @NonNull
    private double amount;
    @CreatedDate
    private Date expenseDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userExpenseId;
}
