package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class DonationService {
    @Autowired
    DonationRepository donationRepo;
    @Autowired
    OrganisationRepository orgRepo;
    @Autowired
    UserRepository userRepo;

    private static final DecimalFormat df = new DecimalFormat("0");

    public String addToTotal() {
        List<Donation> donations = (List<Donation>) donationRepo.findAll();
        double totalSum = 0;

        for (Donation don : donations) {
            totalSum += don.getSum();
        }
        return df.format(totalSum);
    }

    public void quickDonation() {

    }

    public void splitDonation(double sum, String[] organisations, String userName) {
        double sumSplit = sum / organisations.length;

        long userId = userRepo.findByUserName(userName).getId();

        for (int i = 0; i < organisations.length; i++) {
            Long orgId = Long.valueOf(organisations[i]);

            Donation don = new Donation();
            don.setSum(sumSplit);
            don.setOrganisation(orgRepo.findById(orgId).orElse(null));
            don.setUser(userRepo.findById(userId).orElse(null));
            donationRepo.save(don);
        }
    }
}
