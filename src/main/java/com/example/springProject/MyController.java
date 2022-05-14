package com.example.springProject;

import com.example.springProject.dataModels.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MyController {

	@Autowired
	ClientService clientService;

	Logger logger = LogManager.getLogger(MyController.class);

	@GetMapping("/")
	public String homepage() {
		return "home";
	}

	@GetMapping("/client")
	public String getClient(@ModelAttribute Client clientForSearch, Model model) {
		model.addAttribute("client", clientForSearch);
		return "client";
	}

	@PostMapping("/client")
	public String postClient(@ModelAttribute Client clientForSearch, Model model,
							 @ModelAttribute Client clientForUpdate)
	{
		Client clientInDatabase = clientService.findById(clientForSearch.getId());
		if (clientInDatabase == null) {
			logger.info("Client not found by id {}", clientForSearch.getId());
			return "clientisnull";
		}
		model.addAttribute(clientInDatabase);
		model.addAttribute("client2", clientForUpdate);
		return "clientnotnull";
	}

	@GetMapping("/clientupdate")
	public String clientUpdate(@ModelAttribute Client client,
							   Model model) {
		model.addAttribute("client", client);
		return "clientupdate";
	}

	@PostMapping("/clientupdate")
	public String clientWasUpdated(@ModelAttribute Client client, Model model) {
		model.addAttribute("client", client);
		clientService.updateById(client.getId(), client.getFirstname(), client.getLastname());
		return "clientwasupdated";
	}

	@GetMapping("/count")
	public String getCount(Model model) {
		model.addAttribute("count", Long.toString(clientService.count()));
		return "count";
	}

	@ResponseBody
	@GetMapping("/clients")
	public String clients() {
		return clientService.findAll().toString();
	}
}
