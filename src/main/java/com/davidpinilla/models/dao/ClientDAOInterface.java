package com.davidpinilla.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.davidpinilla.models.entity.Client;


public interface ClientDAOInterface extends PagingAndSortingRepository <Client, Long>{
	
	public void deleteById(Long id);

}
