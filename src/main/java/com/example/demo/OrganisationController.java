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
    private DonationRepository donationRepo;
    @Autowired
    private DonationService donationService;
    @Autowired
    private SecurityUserDetailsService serviceRepo;


   @GetMapping("/organisations")
   public String orgPage(Model model) {
       List<Organisation> organisations = (List<Organisation>) orgRepo.findAll();
       model.addAttribute("organisations", organisations);

       return "organisations";
   }

    @PostMapping("/organisations")
    public String organisationSend(@RequestParam String org, @RequestParam int sum, HttpServletRequest request, Model model) {
        List<Organisation> organisations = (List<Organisation>) orgRepo.findAll();

        String userName = request.getRemoteUser();
        if (userName == null) {
            return "login";
        }

        long userId = userRepo.findByUserName(userName).getId();

        for (Organisation o : organisations) {
            if (o.getName().equals(org)) {
                Donation d = new Donation();
                d.setSum(sum);
                d.setOrganisation(o);
                d.setUser(userRepo.findById(userId).orElseThrow());
                donationRepo.save(d);
                model.addAttribute("totalSum", donationService.addToTotal());

                return "redirect:/helpify";
            }
        }

        return "organisations";
    }

    @GetMapping("/organisations/{id}")
    public String organisation(Model model, @PathVariable Long id) {
        Organisation organisation = orgRepo.findById(id).orElse(null);
        model.addAttribute("organisation", organisation);

        return "pickOrg";
    }
}




