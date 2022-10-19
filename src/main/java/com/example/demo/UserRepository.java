package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@Service
public interface UserRepository extends CrudRepository<User, Long> {


    User findByUserName(String name);
    User findByPassword(String password);
    List <User> findAllById(Long id);

}
