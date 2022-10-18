package com.example.demo;

import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size (min=4, max=12)
    @Column(name="USERNAME", nullable = false, unique = true)
    private String userName;
    @Size(min=1, max=30)
    @Column(name="FIRSTNAME")
    private String firstName;
    @Size(min=1, max = 30)
    @Column(name="LASTNAME")
    private String lastName;
    @Size(min=6)
    @Column(name="PASSWORD")
    private String password;
    @Email
    @Column(name="EMAIL")
    private String email;

    @Column(name="ISADMIN")
    private boolean isAdmin;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Donation> donations = new ArrayList<>();

    // behöver vi göra en annotering till organisationer här ?

    public User() {
    }

    public User(Long id, String userName, String firstName, String lastName, String password, String email, Boolean admin) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
       this.isAdmin = admin;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
