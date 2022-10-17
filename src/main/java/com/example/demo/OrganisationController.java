package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OrganisationController {

    @Autowired
    private OrganisationRepository orgRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private SecurityUserDetailsService serviceRepo;

   //startsidan för de organisationer vi har tillgängliga att donera till.
    @GetMapping("/organisations")
    public String orgPage(Model model, HttpSession session, HttpServletRequest request) {
        //letar efter en användare
        String userName = request.getRemoteUser();
        for (User user : userRepo.findAll()) {
            if (user.getUserName().equals(userName)) {
                session.setAttribute("userName", user.getUserName());
                session.setAttribute("firstname", user.getFirstName());
                session.setAttribute("lastname", user.getLastName());
                session.setAttribute("email", user.getEmail());
                session.setAttribute("donations", user.getDonations());
                session.setAttribute("id", user.getId());
                session.setAttribute("password", user.getPassword());
            }
        }
        // visar listan med bild och beskrivningstext om organisationen
        List<Organisation> organisations = (List<Organisation>) orgRepo.findAll();
        model.addAttribute("organisations", organisations);
        return "organisations";
    }

    @PostMapping("/organisations")
    public String organisationSend(Model model, HttpSession session, @RequestParam(value = "id") Long id, HttpServletRequest request) {
        //om ingen användare hittas återvänd till startstida
        if (request.getRemoteUser() == null) {
            return "SignUp";
        }
        Organisation organisation = orgRepo.findById(id).orElse(null);
        String user1 = request.getRemoteUser();
        Long userId = 0L;

        for (User user : userRepo.findAll()) {
            if (user.getUserName().equals(request.getRemoteUser())) {
                userId = user.getId();
            }
        }
        User user = userRepo.findById(userId).orElse(null);
        // fixa senare, men något som inte stämmer här
        return "organisations";
    }


    // test, den här behövs egentligen inte hittar organisation via ID
    @GetMapping("/organisations/{id}")
    public String OrgById(Model model, @PathVariable Long id, HttpSession session) {

        Organisation organisation = orgRepo.findById(id).orElse(null);
        model.addAttribute("organisation", organisation);
        return "pickOrg";
    }
    @PostMapping("/organisations/{id}")
    public String postOrgById(Model model, @PathVariable Long id, HttpSession session, @RequestParam String UserName, @RequestParam String password) {
        Organisation organisation = orgRepo.findById(id).orElse(null);
        model.addAttribute("organisation", organisation);
        return "donation";
    }
}




