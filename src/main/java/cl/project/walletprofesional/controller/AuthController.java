package cl.project.walletprofesional.controller;

import cl.project.walletprofesional.entity.User;
import cl.project.walletprofesional.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class AuthController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private static final int MAX_LOGIN_ATTEMPTS = 3;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginForm(HttpSession session, Model model) {
        if (session.getAttribute("loginAttempts") == null) {
            session.setAttribute("loginAttempts", 0);
        }
        return "login.jsp";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Model model, HttpSession session) {
        logger.debug("Attempting to authenticate user with email: {}", email);

        Integer loginAttempts = (Integer) session.getAttribute("loginAttempts");
        if (loginAttempts == null) {
            loginAttempts = 0;
        }

        if (loginAttempts == MAX_LOGIN_ATTEMPTS) {
            model.addAttribute("error", "Ha excedido el intento de ingresos. Intente más tarde.");
            return "login.jsp";
        }

        User user = userService.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            logger.debug("Authentication successful for user with email: {}", email);
            session.setAttribute("user", user);
           /* model.addAttribute("user", user);*/
            session.setAttribute("loginAttempts", 0);
            return "dashboard.jsp";
        } else {
            logger.debug("Invalid credentials for user with email: {}", email);
            loginAttempts++;
            session.setAttribute("loginAttempts", loginAttempts);
            model.addAttribute("error", "Invalid credentials");
            return "login.jsp";
        }
    }

   @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        /*return "redirect:/login";*/
        return "index.jsp";
    }

   @GetMapping("/index")
    public String showIndex() {
        return "index.jsp";
    }
}
