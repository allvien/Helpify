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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.beans.Encoder;
import java.util.ArrayList;
import java.util.List;

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
    public String myPage(HttpSession session, HttpServletRequest request, Model model) {
        String userName = request.getRemoteUser();
        if (userName == null) {
            return "login";
        }
        User user = userRepo.findByUserName(userName);

        model.addAttribute("user", user);

        List<Donation> donations = new ArrayList<>();
        //for (Donation donation: donations) {
        //}
        //fylla donationslistan. loopa user.donations. finns organisation i user donations? om inte skapa objekt som adderas i -->
        //donationslista summerad

        for (Donation donation : user.getDonations()) {
            boolean foundDonation = false;

            for (Donation don : donations) {
                if (donation.getOrganisation().getId() == don.getOrganisation().getId()) {
                    don.setSum(donation.getSum() + don.getSum());
                    foundDonation = true;
                }
            }
            if (!foundDonation) {
                donations.add(donation);
            }
        }

        model.addAttribute("donations", donations);
        //list user donations
        if (user.getDonations().isEmpty()){
            model.addAttribute("noDonation", user.getDonations());
        }

        /*List<Donation> donations= donRepo.findAllByOrganisation();
        Organisation org = new Organisation();

        for (Donation orgName : donations){
            if (orgName.getId() ==

        }*/

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
}
