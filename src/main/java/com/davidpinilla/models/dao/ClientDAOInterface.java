package com.davidpinilla.models.dao;

import java.util.List;

import com.davidpinilla.models.entity.Client;


public interface ClientDAOInterface {
	public List<Client> findAll();
	public void save(Client client);
	public Client findOne(Long id);
	public void delete(Long id);
}
