package com.example.expenseTracker.service;

import com.example.expenseTracker.entity.ExpenseTrackerEntity;
import com.example.expenseTracker.repository.ExpenseTrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ExpenseTrackerService implements ETServiceImp {

    @Autowired
    private ExpenseTrackerRepository expenseTrackerRepository;

    public ExpenseTrackerEntity saveExpense( ExpenseTrackerEntity expenseTrackerEntity){

        return expenseTrackerRepository.save(expenseTrackerEntity);
    }

    public ExpenseTrackerEntity saveUpdatedExpense( Integer id, ExpenseTrackerEntity expenseTrackerEntity) throws Exception {

       try {
           ExpenseTrackerEntity expenseTrackerEntityUpdated = expenseTrackerRepository.findById(id).get();
            if(expenseTrackerEntityUpdated != null){
                expenseTrackerEntityUpdated.setAmount(expenseTrackerEntity.getAmount());
                expenseTrackerEntityUpdated.setDescription(expenseTrackerEntity.getDescription());
                expenseTrackerEntityUpdated.setCategory(expenseTrackerEntity.getCategory());
            }
           return expenseTrackerRepository.save(expenseTrackerEntityUpdated);
        } catch (Exception e) {
            System.out.println("Id " + id + " is not present in DB, please give the valid ID : " + e.getMessage());
        }
        throw new Exception("Expense not found with id: " + id);
    }

    public void deleteExpenseById(Integer id){
        expenseTrackerRepository.deleteById(id);
    }


    public List<ExpenseTrackerEntity> getExpense(){
        return expenseTrackerRepository.findAll();
    }

    public List<ExpenseTrackerEntity> getExpensesById(String Id){
        return expenseTrackerRepository.findAllById(List.of(Integer.parseInt(Id)));
    }

}
