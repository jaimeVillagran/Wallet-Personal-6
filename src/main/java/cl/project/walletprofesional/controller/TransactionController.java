package cl.project.walletprofesional.controller;

import cl.project.walletprofesional.entity.Transaction;
import cl.project.walletprofesional.entity.User;
import cl.project.walletprofesional.service.impl.TransactionServiceImpl;
import cl.project.walletprofesional.service.impl.UserServiceImpl;
/*import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;*/
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
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

        List<Transaction> transactions = transactionService.findTransactionsByUserId(user.getUserId());
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
            model.addAttribute("error", "Debe iniciar sesión para acceder a esta página");
            return "/login.jsp";
        }

        transactionService.deposit(user, amount);
        user = userServiceImpl.updateUserBalance(user, amount); // Actualiza el saldo del usuario
        session.setAttribute("user", user);

        model.addAttribute("message", "Depósito exitoso");
        return "/dashboard.jsp";
    }

    @GetMapping("/withdraw")
    public String showWithdrawForm() {
        return "withdraw.jsp";
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam("amount") double amount, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "Debe iniciar sesión para acceder a esta página");
            return "/login.jsp";
        }

        boolean success = transactionService.withdraw(user, amount);
        if (success) {
            user = userServiceImpl.updateUserBalance(user, -amount); // Actualiza el saldo del usuario
            session.setAttribute("user", user);
            model.addAttribute("message", "Retiro exitoso");
        } else {
            model.addAttribute("error", "Fondos insuficientes");
        }

        return "dashboard.jsp";
    }

    /*@GetMapping("/user-dashboard")
    public String showDashboard(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "Debe iniciar sesión para acceder a esta página");
            return "login.jsp";
        }
        model.addAttribute("user", user);
        return "dashboard.jsp"; // Asegúrate de tener esta vista
    }*/

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
