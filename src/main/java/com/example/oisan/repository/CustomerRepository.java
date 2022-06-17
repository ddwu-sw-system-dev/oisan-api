package com.example.oisan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.oisan.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	Customer findCustomerByEmail(String email);
	
	Customer findCustomerByNickname(String nickname);
	
	Customer findCustomerByCustomerId(int customerId);
		
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="UPDATE CUSTOMER SET OI_PAY = ?1 WHERE CUSTOMER_ID = ?2", nativeQuery=true)
	int updateOipayByCustomerId(int oiPay, int customerId);
}
