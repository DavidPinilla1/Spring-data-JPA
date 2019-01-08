package com.davidpinilla.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.davidpinilla.models.dao.ClientDAOInterface;
import com.davidpinilla.models.entity.Client;

@Service
public class ClientService implements IClientService {
	@Autowired
	private ClientDAOInterface clientDAO;
	@Override
	@Transactional(readOnly=true)
	public List<Client> findAll() {
		return (List<Client>) clientDAO.findAll();
	}

	@Override
	public void save(Client client) {
	clientDAO.save(client);
	}

	@Override
	@Transactional(readOnly=true)
	public  Client findOne(Long id) {
		return clientDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clientDAO.deleteById(id);
		
	}

	@Override
	public Page<Client> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return clientDAO.findAll(pageable);
	}

}
