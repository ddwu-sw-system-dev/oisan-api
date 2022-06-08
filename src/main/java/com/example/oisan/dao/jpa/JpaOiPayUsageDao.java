package com.example.oisan.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.DataAccessException;

import com.example.oisan.dao.OiPayUsageDao;
import com.example.oisan.domain.OiPayUsage;

public class JpaOiPayUsageDao implements OiPayUsageDao {

	@PersistenceContext
    private EntityManager em;
	
	@Override
	public List<OiPayUsage> getOiPayUsageByCustomerId(int customerId) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OiPayUsage getOiPayUsageById(int oiPayId) throws DataAccessException {
		// TODO Auto-generated method stub
		return em.find(OiPayUsage.class, oiPayId);
	}

	@Override
	public void insertOiPayUsage(OiPayUsage usage) throws DataAccessException {
		// TODO Auto-generated method stub
		em.persist(usage);
	}

}
