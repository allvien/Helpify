package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrganisationService {

    @Autowired
    private OrganisationRepository orgRepo;

    private List<Organisation> organisations;
    public List<Organisation> getOrganisations() {
        return organisations;
    }


    public List<Organisation> getOrganisation(String name) {


        List<Organisation> organisations = new ArrayList<>();
        organisations = orgRepo.findByName(name);

        return organisations;
    }
    public Organisation getOrganisation(Long id) {
        organisations =(List<Organisation>) orgRepo.findAll();
        for (Organisation organisation : organisations) {
            if (organisation.getId().equals(id)) {
                return organisation;
            }
        }
        return null;
    }
}
