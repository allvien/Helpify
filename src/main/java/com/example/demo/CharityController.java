package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class CharityController {

    @Autowired
    private DonationRepository donationRepo;
    @Autowired
    private OrganisationRepository orgRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private DonationService donationService;


    @GetMapping("/")
    public String startPage(Model model, HttpSession session) {

        model.addAttribute("totalSum", donationService.addToTotal());
        return "startPage";
    }

    @PostMapping("/")
    public String startPageAdd(@RequestParam String organisation, @RequestParam int Kr, Model model, HttpServletRequest request, HttpSession session) {
        List<Organisation> organisations = (List<Organisation>) orgRepo.findAll();
        String userName = request.getRemoteUser();
        if (userName == null) {
            return "login";
        }

        long userId = userRepo.findByUserName(userName).getId();

        for (Organisation o : organisations) {
            if (o.getName().equals(organisation)) {
                Donation d = new Donation();
                d.setSum(Kr);
                d.setOrganisation(o);
                d.setUser(userRepo.findById(userId).orElseThrow());
                donationRepo.save(d);

                model.addAttribute("totalSum", donationService.addToTotal());

                return "startPage";
            }
        }
        return "startPage";
    }

    @GetMapping("/donation")
    public String donation(Model model, HttpSession session) {
        List<Organisation> organisations = (List<Organisation>) orgRepo.findAll();
        model.addAttribute("organisations", organisations);
        return "donation";
    }

    @PostMapping("/donation")
    public String donationPost(@RequestParam double sum, @RequestParam String[] organisations, Model model, HttpServletRequest request, HttpSession session) {

        String userName = request.getRemoteUser();
        if (userName == null) {
            return "login";
        }

        donationService.splitDonation(sum, organisations, userName);

        model.addAttribute("totalSum", donationService.addToTotal());

        return "startPage";
    }


    @GetMapping("/aboutUs")
    public String aboutUs() {
        return "aboutUs";
    }

    @GetMapping("/stats")
    public String statistics() {
        return "stats";
    }


   //startsidan för medlemmar
    /*@GetMapping("/signUp")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "saveUser";
    }
    // sparar inlagd användare, och dirigerar vidare till organisationer. obs fungerar ej ännu
    @PostMapping("/saveUser")
    public String setUser (@ModelAttribute User user) {
        userRepo.save(user);
        return "redirect:/organisations";
    }
*/
    @GetMapping("/admin")
    public String adminPage() {
        return "adminPage";
    }
}

