package com.example.demo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface OrganisationRepository extends CrudRepository<Organisation, Long> {
    List <Organisation> findByName(String name);


    List <Organisation> findAllById(Long id);
}


/* List<Organisation> findAllByOrderByCategory();
    List <Organisation> findByName(String name);
    @Query(value = "SELECT * FROM ORGANISATION ORDER BY NAME", nativeQuery = true)
    List<Organisation> sortByName();

   // count of Customer table
    long count = repository.count();

    // find one customer by id
    Customer customer = repository.findById(1L).get();
// save new customer
repository.save(new Customer("Donald", "Duck"));
// delete customer
repository.deleteById(1L);


@ManyToOne
private Category favoriteCategory;*/
