package com.example.oisan.dao;

import java.util.List;

import com.example.oisan.domain.OiPayUsage;

public interface OiPayUsageDao {
	List<OiPayUsage> getOiPayUsageByUserId(int userId);
	
	OiPayUsage getOiPayUsage(int oiPayId);
	
	void insertOiPayUsage(OiPayUsage usage);
}
