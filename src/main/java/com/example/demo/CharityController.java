package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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


    @GetMapping("/helpify")
    public String startPage(Model model) {

        model.addAttribute("totalSum", donationService.addToTotal());
        return "startPage";
    }

    @PostMapping("/helpify")
    public String startPageAdd(@RequestParam String organisation, @RequestParam int Kr, Model model) {
        List<Organisation> organisations = (List<Organisation>) orgRepo.findAll();

        for (Organisation o : organisations) {
            if (o.getName().equals(organisation)) {
                Donation d = new Donation();
                d.setSum(Kr);
                d.setOrganisation(o);
                donationRepo.save(d);

                model.addAttribute("totalSum", donationService.addToTotal());
            }
        }
        return "startPage";
    }

    @GetMapping("/donation")
    public String donation(Model model) {
        List<Organisation> organisations = (List<Organisation>) orgRepo.findAll();
        model.addAttribute("organisations", organisations);
        return "donation";
    }

    @PostMapping("/donation")
    public String donationPost(@RequestParam double sum, @RequestParam String[] organisations, Model model, HttpServletRequest request) {

        String userName = request.getRemoteUser();
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

    @GetMapping("/admin")
    public String adminPage() {
        return "adminPage";
    }
}

