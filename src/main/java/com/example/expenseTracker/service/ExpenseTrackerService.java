package com.example.expenseTracker.service;

import com.example.expenseTracker.entity.ExpenseTrackerEntity;
import com.example.expenseTracker.entity.UserEntity;
import com.example.expenseTracker.repository.ExpenseTrackerRepository;
import com.example.expenseTracker.repository.UserRepository;
import com.example.expenseTracker.security.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseTrackerService implements ETServiceImp {

    @Autowired
    private ExpenseTrackerRepository expenseTrackerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTService jwtService;


    public ExpenseTrackerEntity saveExpense(ExpenseTrackerEntity expenseTrackerEntity) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String username = authentication.getName();

        UserEntity user = userRepository
                .findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        expenseTrackerEntity.setUserExpenseId(user);

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




    public List<ExpenseTrackerEntity> getExpense() {

        String authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication().getName();

        UserEntity userFromMobileNumber = userRepository.findByMobileNumber(authentication)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Integer userId = userFromMobileNumber.getUserId();
        return expenseTrackerRepository.findByUserExpenseId(userFromMobileNumber);
    }

    public List<ExpenseTrackerEntity> getExpensesById(String Id){
        return expenseTrackerRepository.findAllById(List.of(Integer.parseInt(Id)));
    }

}
