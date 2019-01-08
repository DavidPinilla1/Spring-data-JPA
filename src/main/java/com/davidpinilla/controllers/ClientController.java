package com.davidpinilla.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.davidpinilla.controllers.util.paginator.PageRender;
import com.davidpinilla.models.entity.Client;
import com.davidpinilla.models.service.IClientService;

@Controller
@SessionAttributes("client")
public class ClientController {
	@Autowired
	private IClientService clientService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@RequestParam(name = "page", defaultValue = "0") int page, Model theModel) {
		Pageable pageRequest = PageRequest.of(page, 3);
		Page<Client> clients = clientService.findAll(pageRequest);
		PageRender<Client> pageRender = new PageRender("/list", clients);
		theModel.addAttribute("title", "Client list");
		theModel.addAttribute("clients", clients);
		theModel.addAttribute("page", pageRender);
		return "list";
	}

	@GetMapping(value = "/form")
	public String create(Map<String, Object> model) {
		Client client = new Client();
		model.put("client", client);
		model.put("title", "New Client");
		return "form";
	}

	@RequestMapping(value = "/form/{id}")
	public String edit(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Client client = null;
		if (id > 0) {
			client = clientService.findOne(id);
			if (client == null) {
				flash.addFlashAttribute("error", "Client not found in the database");
				return "redirect:/list";
			}
		} else {
			flash.addFlashAttribute("error", "Client id can not be 0!");
			return "redirect:/list";

		}
		model.put("client", client);
		model.put("title", "Edit Client");
		return "form";
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String save(@Valid Client client, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {

		model.addAttribute("title", "Client Form");
		if (result.hasErrors()) {
			return "form";
		}
		String Flashmessage = client.getId() != null ? "Client edited successfully" : "Client created successfully";
		clientService.save(client);
		status.setComplete();
		flash.addFlashAttribute("success", Flashmessage);
		return "redirect:list";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			clientService.delete(id);
			flash.addFlashAttribute("success", "Client deleted successfully!");

		}
		return "redirect:/list";
	}
}
