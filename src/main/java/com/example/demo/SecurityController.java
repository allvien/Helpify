package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class SecurityController {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    PasswordEncoder encoder;


    //logga in med användare från databasen
    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @GetMapping("/signup")
    public String addUser(Model model) {
        model.addAttribute("user", new User());

        return "saveUser";
    }

    @PostMapping("/signup")
    public String setUser (@ModelAttribute @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "saveUser";
        }

        if (userRepo.findByUserName(user.getUserName()) == null) {
            user.setPassword(encoder.encode(user.getPassword()));
            userRepo.save(user);
        }

        return "login";
    }

    @GetMapping("/myPage")
    public String myPage() {

        return "myPage";
    }

    @GetMapping("/logout")
    public String logout() {

        return "startPage";
    }
}
