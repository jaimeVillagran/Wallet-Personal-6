package cl.project.walletprofesional.controller;

import cl.project.walletprofesional.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String home() {
        return "index.jsp";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "Debe iniciar sesión para acceder a esta página");
            return "login.jsp";
        }
        model.addAttribute("user", user);
        return "dashboard.jsp";
    }


  /*  @GetMapping("/")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("index.jsp");
        return mav;

    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login.jsp");
        return mav;
    }

    @GetMapping("/register")
    public ModelAndView register() {
        ModelAndView mav = new ModelAndView("register.jsp");
        return mav;
    }*/
}
