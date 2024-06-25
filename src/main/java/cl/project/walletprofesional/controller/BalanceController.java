package cl.project.walletprofesional.controller;

import cl.project.walletprofesional.entity.User;
import cl.project.walletprofesional.service.impl.TransactionServiceImpl;
import cl.project.walletprofesional.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BalanceController {

    private final UserServiceImpl userServiceImpl;
    private final TransactionServiceImpl transactionService;

    public BalanceController(UserServiceImpl userServiceImpl,
                             TransactionServiceImpl transactionService) {
        this.userServiceImpl = userServiceImpl;
        this.transactionService = transactionService;
    }

    @GetMapping("/balance")
    public String showBalance(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "Debe iniciar sesión para acceder a esta página");
            return "redirect:/login.jsp";
        }

        model.addAttribute("balance", user.getBalance());
        return "balance.jsp";
    }

    @GetMapping("/balance/transactions")
    public String getUserBalanceAndTransactions (@RequestParam("user_id") long userId, Model model) {
        User user = userServiceImpl.getUserById(userId);
        if (user == null) {
            model.addAttribute("error", "Usuario no encontrado");
            return "dashboard.jsp";
        }

        model.addAttribute("transactions", transactionService.findTransactionsByUserId(userId));
        model.addAttribute("balance", user.getBalance());
        return "balance.jsp";
    }
}
