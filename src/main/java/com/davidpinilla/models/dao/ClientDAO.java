package com.davidpinilla.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ch.qos.logback.core.net.server.Client;

@Repository("ClientDAOJPA")
public class ClientDAO implements ClientDAOInterface{
	@PersistenceContext
	private EntityManager em;
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Client> findAll() {
	
		return em.createQuery("from Client").getResultList();
	}

}
