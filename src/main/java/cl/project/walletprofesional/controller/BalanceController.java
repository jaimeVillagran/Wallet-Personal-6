package cl.project.walletprofesional.controller;

import cl.project.walletprofesional.entity.Transaction;
import cl.project.walletprofesional.entity.User;
import cl.project.walletprofesional.service.impl.TransactionServiceImpl;
import cl.project.walletprofesional.service.impl.UserServiceImpl;
/*import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;*/
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BalanceController {

    private final UserServiceImpl userServiceImpl;
    private final TransactionServiceImpl transactionService;

    public BalanceController(UserServiceImpl userServiceImpl, TransactionServiceImpl transactionService) {
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
        Double balance = user.getBalance();
        List<Transaction> transactions = transactionService.findTransactionsByUserId(user.getUserId());

        model.addAttribute("balance", balance);
        model.addAttribute("transactions", transactions);

        return "balance.jsp";
    }
}
