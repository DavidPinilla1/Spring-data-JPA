package com.davidpinilla.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.davidpinilla.models.dao.ClientDAOInterface;

@Controller
public class ClientController {
	@Autowired
	private ClientDAOInterface clientDAO;
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(Model theModel) {
		theModel.addAttribute("title", "Client list");
		return "list";
	}
}
