package com.example.expenseTracker.restController;

import com.example.expenseTracker.bean.ExpenseTrackerBean;
import com.example.expenseTracker.entity.ExpenseTrackerEntity;
import com.example.expenseTracker.service.ExpenseTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ExpenseTrackerController {

    @Autowired
    private ExpenseTrackerService expenseTrackerService;

    @PostMapping("/addExpense")
    public ExpenseTrackerEntity addExpense(@RequestBody ExpenseTrackerEntity expenseTrackerEntity) {

        return expenseTrackerService.saveExpense(expenseTrackerEntity);
    }
    @GetMapping("/getExpenses")
    public List<ExpenseTrackerEntity> getExpenses() {
        return expenseTrackerService.getExpense();
    }

    @GetMapping("/getExpenses/id={id}")
    public List<ExpenseTrackerEntity> getExpensesById(@PathVariable String id) {
        return expenseTrackerService.getExpensesById(id);
    }

    @GetMapping("/getExpensesByRequestParam")
    public List<ExpenseTrackerEntity> getExpensesByIdRequestParam(@RequestParam String id) {
        return expenseTrackerService.getExpensesById(id);
    }

    @PutMapping("/updateById/{id}")
    public ExpenseTrackerEntity updateById(@PathVariable Integer id, @RequestBody ExpenseTrackerEntity expenseTrackerEntity) throws Exception {
        return expenseTrackerService.saveUpdatedExpense(id,expenseTrackerEntity);
    }

    @DeleteMapping("/deleteExpensesById/{id}")
    public String deleteById(@PathVariable Integer id) {
        expenseTrackerService.deleteExpenseById(id);
        return "Expense Id " + id +" deleted Successfully";
    }

}
