package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface DonationRepository extends CrudRepository<Donation, Long> {
}
