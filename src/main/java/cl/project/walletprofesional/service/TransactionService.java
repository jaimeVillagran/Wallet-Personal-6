package cl.project.walletprofesional.service;

import cl.project.walletprofesional.entity.Transaction;
import cl.project.walletprofesional.entity.User;

import java.util.List;

public interface TransactionService {
    List<Transaction> getAllTransactions();
    Transaction getTransactionById(long id);
    Transaction saveTransaction(Transaction transaction);
    void deleteTransactionById(long id);
    List<Transaction> findTransactionsByUserId(long userId);
    void deposit(User user, double amount);
    boolean withdraw(User user, double amount);
    boolean transfer(User user, User user2, double amount);
}
