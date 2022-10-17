package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Service
@Table(name = "users")
public class UserService {
    private List<User> users;

    @Autowired
    private UserRepository userRepo;

    public List<User> getUsers() {
        return users;
    }
    public List<User> getUserList() {
        return users;
    }

    public User getUser(String userName) {
        users =(List<User>) userRepo.findAll();
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                return user;
            }
        }
        return null;
    }


    public User addUser(User user) {
        User user1 = users.get(users.size() - 1);
        user1.setUserName(user.getUserName() + 1);
        users.add(user);
        return user;
    }

    public User editUser(User user) {
        User userToEdit = this.getUser(user.getUserName());
        if (userToEdit != null) {
            userToEdit.setPassword(user.getPassword());
            userToEdit.setEmail(user.getEmail());
            userToEdit.setFirstName(user.getFirstName());
            userToEdit.setLastName(user.getLastName());


        }
        return user;
    }


    public void deleteUser(String userName) {
        User userToDelete = this.getUser(userName);
        if (userToDelete != null) {
            users.remove(userToDelete);
        }
    }
}
