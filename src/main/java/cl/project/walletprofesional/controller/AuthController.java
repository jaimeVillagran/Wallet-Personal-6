package cl.project.walletprofesional.controller;

import cl.project.walletprofesional.entity.User;
import cl.project.walletprofesional.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final UserService userService;
    private static final int MAX_LOGIN_ATTEMPTS = 3;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"/", "/index"})
    public String index() {
        return "index.jsp";
    }

    @GetMapping("/login")
    public String showLoginForm(HttpSession session) {
        if (session.getAttribute("loginAttempts") == null) {
            session.setAttribute("loginAttempts", 0);
        }
        return "login.jsp";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Model model, HttpSession session) {

        Integer loginAttempts = (Integer) session.getAttribute("loginAttempts");
        if (loginAttempts == MAX_LOGIN_ATTEMPTS) {
            model.addAttribute("error", "Ha excedido el intento de ingresos. Intente más tarde.");
            return "login.jsp";
        }

        User user = userService.findByEmail(email);
        // Modificación: Usar userService.verifyPassword para verificar la contraseña
        if (user != null && userService.verifyPassword(email, password)) {
            session.setAttribute("user", user);
            session.setAttribute("loginAttempts", 0);
            return "dashboard.jsp";
        } else {
            loginAttempts++;
            session.setAttribute("loginAttempts", loginAttempts);
            model.addAttribute("error", "Invalid credentials");
            return "login.jsp";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index.jsp";
    }
}
