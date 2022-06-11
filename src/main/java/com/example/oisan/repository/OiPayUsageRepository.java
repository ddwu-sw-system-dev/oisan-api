package com.example.oisan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.oisan.domain.OiPayUsage;

public interface OiPayUsageRepository extends JpaRepository<OiPayUsage, Integer> {
	List<OiPayUsage> findByCustomerIdOrderByOiPayIdDesc(int customerId);
	
	OiPayUsage findByOiPayId(int oiPayId);
	
	OiPayUsage findTopByCustomerIdOrderByOiPayIdDesc(int customerId);
	
}
