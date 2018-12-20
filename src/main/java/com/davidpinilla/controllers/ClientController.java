package com.davidpinilla.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.davidpinilla.models.dao.ClientDAOInterface;
import com.davidpinilla.models.entity.Client;

@Controller
public class ClientController {
	@Autowired
	private ClientDAOInterface clientDAO;
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model theModel) {
		theModel.addAttribute("title", "Client list");
		theModel.addAttribute("clients", clientDAO.findAll());
		return "list";
	}
	@GetMapping(value="/form")
	public String create (Map<String, Object> model) {
		Client client=new Client();
		model.put("client", client);
		model.put("title", "Client Form");
		return "form";
	}
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String save(@Valid Client client, BindingResult result, Model model ) {

		model.addAttribute("title", "Client Form");
		if(result.hasErrors()) {
			return "form";
		}
		clientDAO.save(client);
		return"redirect:list";
	}
}
