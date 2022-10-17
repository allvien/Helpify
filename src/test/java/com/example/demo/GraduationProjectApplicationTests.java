package com.example.demo;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
class GraduationProjectApplicationTests {

	@Autowired
	OrganisationService organisationService;

	@Autowired
	UserService userService;

	@Test
	void contextLoads() {
	}

	@Test
	public void testGetOrganisation() {
		Assertions.assertEquals("WWF",organisationService.getOrganisation(1L).getName());
	}

	@Test
	public void testGetUser() {
		Assertions.assertEquals("Janne",userService.getUser("qweqwe").getFirstName());
	}
}

