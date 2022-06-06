package com.example.oisan.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.oisan.domain.OiPayUsage;

public interface OiPayUsageDao {
	List<OiPayUsage> getOiPayUsageByCustomerId(int customerId) throws DataAccessException;
	
	OiPayUsage getOiPayUsageById(int oiPayId) throws DataAccessException;
	
	void insertOiPayUsage(OiPayUsage usage) throws DataAccessException;
}
