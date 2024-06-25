package cl.project.walletprofesional.controller.rest;

import cl.project.walletprofesional.entity.User;
import cl.project.walletprofesional.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable long id) {
        userService.deleteUserById(id);
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String password, HttpSession session) {
        User user = userService.findByEmailAndPassword(email, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "User logged in";
        } else {
            return "Invalid credentials";
        }

    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

}
