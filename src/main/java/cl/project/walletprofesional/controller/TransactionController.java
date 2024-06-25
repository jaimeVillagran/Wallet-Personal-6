package cl.project.walletprofesional.controller;

import cl.project.walletprofesional.entity.Transaction;
import cl.project.walletprofesional.entity.User;
import cl.project.walletprofesional.service.impl.TransactionServiceImpl;
import cl.project.walletprofesional.service.impl.UserServiceImpl;
/*import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;*/
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
/*@RequestMapping("/transactions")*/
public class TransactionController {

    private final TransactionServiceImpl transactionService;
    private final UserServiceImpl userServiceImpl;

    public TransactionController(TransactionServiceImpl transactionService, UserServiceImpl userServiceImpl) {
        this.transactionService = transactionService;
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/transactions")
    public String showTransactions(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "Debe iniciar sesión para acceder a esta página");
            return "redirect:/login.jsp";
        }
        // Convertir userId a Long
        List<Transaction> transactions = transactionService.findTransactionsByUserId((long) user.getUserId());
        model.addAttribute("transactions", transactions);
        return "transactions.jsp"; // Asegúrate de tener esta vista
    }

    @GetMapping("/deposit")
    public String showDepositForm() {
        return "deposit.jsp";
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam("amount") double amount, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            log.warn("User not authenticated - redirecting to login");
            model.addAttribute("error", "Debe iniciar sesión para acceder a esta página");
            return "login.jsp";
        }
        if (amount <= 0) {
            if (amount == 0) {
                log.warn("Invalid deposit amount: {}", amount);
                model.addAttribute("error", "No puede depositar un monto de 0");
            } else {
                log.warn("Invalid deposit amount: {}", amount);
                model.addAttribute("error", "No puede realizar depósitos negativos");
            }
            return "deposit.jsp";
        }
        transactionService.deposit(user, amount);
        log.info("Deposit successful for user: {}", user.getEmail());
        model.addAttribute("message", "Depósito realizado con éxito");
        return "deposit.jsp";
    }

    @GetMapping("/withdraw")
    public String showWithdrawForm() {
        return "withdraw.jsp";
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam("amount") double amount, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            log.warn("User not authenticated - redirecting to login");
            model.addAttribute("error", "Debe iniciar sesión para acceder a esta página");
            return "login.jsp";
        }
        if (amount <= 0) {
            if (amount == 0) {
                log.warn("Invalid withdraw amount: {}", amount);
                model.addAttribute("error", "No puede retirar un monto de 0");
            } else {
                log.warn("Invalid withdraw amount: {}", amount);
                model.addAttribute("error", "No puede realizar retiros negativos");
            }
            return "withdraw.jsp";
        }

        boolean success = transactionService.withdraw(user, amount);
        if (success) {
            user = userServiceImpl.updateUserBalance(user, -amount); // Actualiza el saldo del usuario
            session.setAttribute("user", user);
            log.info("Withdraw successful for user: {}", user.getEmail());
            model.addAttribute("message", "Retiro realizado con éxito");
        } else {
            log.warn("Insufficient funds for user: {}", user.getEmail());
            model.addAttribute("error", "Fondos insuficientes");
        }
        return "withdraw.jsp";
    }


    @GetMapping("/user-dashboard")
    public String showDashboard(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "Debe iniciar sesión para acceder a esta página");
            return "login.jsp";
        }
        model.addAttribute("user", user);
        return "dashboard.jsp"; // Asegúrate de tener esta vista
    }

    @GetMapping("/transaction")
    public String getUserBalanceAndTransactions(@RequestParam("user_id") long userId, Model model) {
        User user = userServiceImpl.getUserById(userId);
        if (user != null) {
            List<Transaction> transactions = transactionService.findTransactionsByUserId(userId);
            model.addAttribute("balance", user.getBalance());
            model.addAttribute("transactions", transactions);
            return "balance.jsp"; // Asegúrate de tener esta vista
        } else {
            model.addAttribute("error", "Usuario no encontrado");
            return "dashboard.jsp"; // Asegúrate de tener esta vista
        }
    }
}
