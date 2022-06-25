package com.example.springProject.controllers;

import com.example.springProject.services.ClientServiceImpl;
import com.example.springProject.entities.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Controller
public class ClientController {

	private final ClientServiceImpl clientService;

	Logger logger = LogManager.getLogger(ClientController.class);

	@Autowired
	public ClientController(ClientServiceImpl clientService) {
		this.clientService = clientService;
	}

	@GetMapping("/")
	public String homepage() {
		return "html/home";
	}


	@GetMapping("/client_info_for_search")
	public String clientSearch(@ModelAttribute Client clientForSearch, Model model) {
		model.addAttribute("client", clientForSearch);
		return "html/client_info_for_search";
	}


	@PostMapping("/client_search")
	public String clientSearch(@ModelAttribute Client clientForSearch, Model model,
							 @ModelAttribute Client clientForUpdate)
	{
		Client clientInDatabase = clientService.findById(clientForSearch.getId());
		if (clientInDatabase == null) {
			return "html/client_is_null";
		}
		model.addAttribute(clientInDatabase);
		model.addAttribute("client2", clientForUpdate);
		return "html/client_is_not_null";
	}


	@PostMapping("/client_info_for_update")
	public String clientIsUpdating(@ModelAttribute Client client,
							   Model model) {
		model.addAttribute("client", client);
		return "html/client_info_for_update";
	}


	@PostMapping("/client_updated")
	public String clientUpdated(@ModelAttribute Client client, Model model) {
		model.addAttribute("client", client);
		clientService.updateClient(client.getId(), client.getFirstname(), client.getLastname());
		return "html/client_updated";
	}


	@PostMapping("/client_deleted")
	public String client(@ModelAttribute Client client, Model model) {
		clientService.deleteClientById(client.getId());
		return "html/client_deleted";
	}


	@GetMapping("/count")
	public String getCount(Model model) {
		model.addAttribute("count", Long.toString(clientService.count()));
		return "html/count";
	}


	@ResponseBody
	@GetMapping("/all_clients")
	public String clients() {

		StringBuilder stringBuilder = new StringBuilder();

		Comparator<Client> comparator = new Comparator<Client>() {
			@Override
			public int compare(Client c1, Client c2) {
				if (c1.getId() == c2.getId()) {
					return 0;
				}
				return c1.getId() - c2.getId() > 0 ? 1 : -1;
			}
		};

		List<Client> clientList = clientService.findAll();
		clientList.sort(comparator);

		for (Client client : clientList) {
			stringBuilder.append(client.toString());
		}
		return stringBuilder.toString();
	}
}
