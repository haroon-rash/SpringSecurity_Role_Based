package org.example.springsecurity.springsecurity.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {


    @GetMapping("/access-denied")
    public String accessDenied() {
        return "error"; // OR redirect back: return "redirect:/user";
    }

    @GetMapping("/login")
    public String Login() {
        return "Login";
    }

    @GetMapping("/home")
    public String Home() {
        return "Home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Admin";
    }

    @GetMapping("/user")
    public String user() {
        return "User";
    }


}
