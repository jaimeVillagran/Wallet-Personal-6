package cl.project.walletprofesional.controller;

import cl.project.walletprofesional.entity.User;
import cl.project.walletprofesional.service.UserService;
/*import org.springframework.security.crypto.password.PasswordEncoder;*/
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    private final UserService userService;
    /*private final PasswordEncoder passwordEncoder;*/

    public RegistrationController(UserService userService /*PasswordEncoder passwordEncoder*/) {
        this.userService = userService;
        /*this.passwordEncoder = passwordEncoder;*/
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        return "register.jsp";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam("first_name") String firstName,
                               @RequestParam("last_name") String lastName,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password,
                               Model model) {
        // Crear y guardar el nuevo usuario
        User user = new User();
        user.setFirstname(firstName);
        user.setLastname(lastName);
        user.setEmail(email);
        /*user.setPassword(passwordEncoder.encode(password)); // Encriptar la contraseña*/
        user.setPassword(password); // Guardar la contraseña sin encriptar
        user.setBalance(0.0); // Inicializar balance con 0.0

        userService.saveUser(user);
        model.addAttribute("message", "Usuario registrado exitosamente");
        return "login.jsp"; // Redirigir al login después del registro
    }
}
