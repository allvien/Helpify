package com.example.demo;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Organisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Size(min=4, max=500)
    private String description;
    private String category;
    @OneToMany(mappedBy = "organisation", cascade = CascadeType.ALL)
    private List<Donation> donations = new ArrayList<>();


    public Organisation() {
    }

    public Organisation(Long id, String name, String description, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }
}
 /* @OneToMany(mappedBy = ”favoriteCategory", cascade = CascadeType.ALL)
            private List<Customer> coustomers = new ArrayList<>(); */