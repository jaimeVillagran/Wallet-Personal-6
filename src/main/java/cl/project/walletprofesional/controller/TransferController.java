package cl.project.walletprofesional.controller;

import cl.project.walletprofesional.entity.User;
import cl.project.walletprofesional.service.TransactionService;
import cl.project.walletprofesional.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TransferController {

    private final UserService userService;
    private final TransactionService transactionService;

    public TransferController(UserService userService, TransactionService transactionService) {
        this.userService = userService;
        this.transactionService = transactionService;
    }

    @GetMapping("/transfer")
    public String showTransferForm(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "Debe iniciar sesión para acceder a esta página");
            return "redirect:/login";
        }
        List<User> users = userService.getAllUsers().stream()
                .filter(u -> !u.getEmail().equals(user.getEmail()))
                .collect(Collectors.toList());
        model.addAttribute("users", users);
        model.addAttribute("user", user);
        return "transfer.jsp";
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam("recipientEmail") String recipientEmail,
                           @RequestParam("amount") Double amount,
                           Model model, HttpSession session) {
        User sender = (User) session.getAttribute("user");
        if (sender == null) {
            model.addAttribute("error", "Debe iniciar sesión para acceder a esta página");
            return "redirect:/login.jsp";
        }
        if (recipientEmail == null || recipientEmail.isEmpty() || amount == null || amount <= 0) {
            model.addAttribute("error", "Debe completar todos los campos");
            return showTransferForm(model, session); // Ensure the form is available again
        }

        User recipient = userService.findByEmail(recipientEmail);
        if (recipient == null) {
            model.addAttribute("error", "El destinatario no existe");
            return showTransferForm(model, session); // Ensure the form is available again
        }

        boolean success = transactionService.transfer(sender, recipient, amount);
        if (success) {
            sender = userService.updateUserBalance(sender, -amount);
            session.setAttribute("user", sender);
            model.addAttribute("message", "Transferencia realizada con éxito");
        } else {
            model.addAttribute("error", "Fondos insuficientes");
        }
        return showTransferForm(model, session); // Ensure the form is available again with success message
    }
}
