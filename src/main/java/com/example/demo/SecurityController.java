package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.beans.Encoder;

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

    @GetMapping("/SignUp")
    public String addUser(Model model, HttpServletRequest request, HttpSession session) {
        model.addAttribute("user", new User());
        /*String username = request.getRemoteUser();
        // check if there is an active user on the "session"
        for (User user : userRepo.findAll()) {
            if (user.getUserName().equals(username)) {
                model.addAttribute("user", user);
                session.setAttribute("userName", user.getUserName());
                session.setAttribute("password", user.getPassword());
            }
        }*/
        return "saveUser";
    }

    @PostMapping("/SignUp")
    public String setUser (@ModelAttribute @Valid User user, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            return "saveUser";
        }
        /*
        user.setUserName((String)session.getAttribute("userName"));
        user.setPassword((String)session.getAttribute("password"));
        userRepo.save(user);
         System.out.println("POST SIGNUP"); */
        // only save user if it doesnt already exist in database
        if (userRepo.findByUserName(user.getUserName()) == null) {

            user.setPassword(encoder.encode(user.getPassword()));
            userRepo.save(user);
        }
        return "login";
    }

    @GetMapping("/myPage")
    public String myPage(HttpSession session, HttpServletRequest request, Model model) {
       /* String userName = request.getRemoteUser();
        for (User user : userRepo.findAll()) {
            if (user.getUserName().equals(userName)) {
                model.addAttribute("user", user);
                session.setAttribute("userName", user.getUserName());
                session.setAttribute("password", user.getPassword());
            }
        } */
        String userName = request.getRemoteUser();
        if (userName == null) {
            return "login";
        }
        User user = userRepo.findByUserName(userName);

        model.addAttribute("user", user);

        return "myPage";

    }

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse res){
        /*
        session.removeAttribute("userName"); // this would be an ok solution
        session.invalidate(); // you could also invalidate the whole session, a new session will be created the next request
        Cookie cookie = new Cookie("JSESSIONID", "");
        cookie.setMaxAge(0);
        res.addCookie(cookie); */
        return "startPage";
    }

/*
    @GetMapping("/admin1")//obs lagt till 1
    public String admin() {
        return "admin";
    }*/
}
 /*
    @PostMapping("/SignUp")
    public String setUser (@ModelAttribute @Valid User user, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            return "saveUser";
        } return "/SignUp";
    }*/
//
//    @PostMapping("/myPage")
//    public String userPost(@ModelAttribute User user, HttpSession session) {
//        user.setUserName((String)session.getAttribute("userName"));
//        user.setPassword((String)session.getAttribute("password"));
//        userRepo.save(user);
//        return "myPage";
//    }sparar inlagd användare, och dirigerar vidare till organisationer. obs fungerar ej ännu