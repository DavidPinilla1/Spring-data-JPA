package com.davidpinilla.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.davidpinilla.models.entity.Client;


public interface ClientDAOInterface extends CrudRepository <Client, Long>{
	
	public void deleteById(Long id);

}
