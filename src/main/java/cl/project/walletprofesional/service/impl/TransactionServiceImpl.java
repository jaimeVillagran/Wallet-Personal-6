package cl.project.walletprofesional.service.impl;

import cl.project.walletprofesional.entity.Transaction;
import cl.project.walletprofesional.entity.User;
import cl.project.walletprofesional.repository.TransactionRepository;
import cl.project.walletprofesional.service.TransactionService;
import cl.project.walletprofesional.service.UserService;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.List;


@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserService userService;

    public TransactionServiceImpl(TransactionRepository transactionRepository, UserService userService) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
    }

    @Override
    public List<Transaction> findTransactionsByUserId(Long userId) {
        User user = userService.getUserById(userId);
        return transactionRepository.findByUserOrderByTransactionDateDesc(user);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction getTransactionById(long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransactionById(long id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public void deposit(User user, double amount) {
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setAmount(amount);
        transaction.setTransactionType("DEPOSITO");
        transaction.setTransactionDate(new Timestamp(
                new java.util.Date().getTime()));
        transactionRepository.save(transaction);

        userService.updateUserBalance(user, amount);
    }

    @Override
    public boolean withdraw(User user, double amount) {
        if (user.getBalance() >= amount) {
            Transaction transaction = new Transaction();
            transaction.setUser(user);
            transaction.setAmount(-amount);
            transaction.setTransactionType("RETIRO");
            transaction.setTransactionDate(new Timestamp(
                    new java.util.Date().getTime()
            ));
            transactionRepository.save(transaction);

            userService.updateUserBalance(user, -amount);
            return true;
        }
        return false;
    }

    @Override
    public boolean transfer(User sender, User receiver, double amount) {
        if (sender.getBalance() >= amount) {
            // Crear transacción de envío
            Transaction sendTransaction = new Transaction();
            sendTransaction.setUser(sender);
            sendTransaction.setAmount(-amount);
            sendTransaction.setTransactionType("TRANSFERENCIA");
            sendTransaction.setTransactionDate(new Timestamp(
                    new java.util.Date().getTime()
            ));
            transactionRepository.save(sendTransaction);

            // Actualizar saldo del remitente
            userService.updateUserBalance(sender, -amount);

            // Crear transacción de recepción
            Transaction receiveTransaction = new Transaction();
            receiveTransaction.setUser(receiver);
            receiveTransaction.setAmount(amount);
            receiveTransaction.setTransactionType("TRANSFERENCIA");
            receiveTransaction.setTransactionDate(new Timestamp(
                    new java.util.Date().getTime()
            ));
            transactionRepository.save(receiveTransaction);

            // Actualizar saldo del receptor
            userService.updateUserBalance(receiver, amount);

            return true;
        }
        return false;
    }
}
