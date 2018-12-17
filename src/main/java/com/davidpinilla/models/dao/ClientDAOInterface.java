package com.davidpinilla.models.dao;

import java.util.List;

import ch.qos.logback.core.net.server.Client;

public interface ClientDAOInterface {
	public List<Client> findAll();
}
