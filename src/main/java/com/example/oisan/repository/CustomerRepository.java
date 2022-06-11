package com.example.oisan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.oisan.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	Customer findCustomerByEmail(String email);
	
	Customer findCustomerByNickname(String nickname);
	
	Customer findCustomerByCustomerId(int customerId);
		
}
