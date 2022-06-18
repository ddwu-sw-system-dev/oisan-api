package com.example.oisan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.oisan.domain.Customer;
import com.example.oisan.domain.Post;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	Customer findCustomerByEmail(String email);
	
	Customer findCustomerByNickname(String nickname);
	
	Customer findCustomerByCustomerId(int customerId);
		
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="UPDATE CUSTOMER SET OI_PAY = ?1 WHERE CUSTOMER_ID = ?2", nativeQuery=true)
	int updateOipayByCustomerId(int oiPay, int customerId);
	
	@Query(value="select post_id\r\n"
			+ "from post p1\r\n"
			+ "where exists (select l.post_id \r\n"
			+ "from customer c, like_post l \r\n"
			+ "where c.customer_id=l.customer_id and l.post_id=p1.post_id and l.customer_id=?1)", nativeQuery=true)
	List<Integer> findLikePostByCustomerId(int customerId);
}
