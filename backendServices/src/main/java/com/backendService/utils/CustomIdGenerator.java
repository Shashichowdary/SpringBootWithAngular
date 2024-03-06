package com.backendService.utils;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.backendService.repository.UserRespository;

@Component
public class CustomIdGenerator implements IdentifierGenerator{
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	UserRespository repository;
	
	@Autowired
	ApplicationContextProvider applicationContextProvider;
	
	public static final String UID = "UID";

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		if(repository==null) {
			repository= ApplicationContextProvider.getBean(UserRespository.class);
		}
		int nextSequence =repository.generateSequence();
		
		return UID+String.format("%04d", nextSequence);
	}

}
