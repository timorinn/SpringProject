package com.example.SpringProject_1;

import com.example.SpringProject_1.dataModels.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MyController {

	@Autowired
	ClientService clientService;

	@GetMapping("/")
	public String homepage() {
		return "home";
	}

	@GetMapping("/client")
	public String getClient(@ModelAttribute Client client, Model model) {
		model.addAttribute("client", client);
		return "client";
	}

	@PostMapping("/client")
	public String postSubmit(@ModelAttribute Client client, Model model,
							 @ModelAttribute Client client2)
	{
		Client clientInDatabase = clientService.findById(client.getId());

		if (clientInDatabase == null) {
			return "clientisnull";
		}

		model.addAttribute(clientInDatabase);
		model.addAttribute("client2", client2);
		return "clientnotnull";
	}

	@GetMapping("/clientupdate")
	public String clientUpdate(@ModelAttribute Client client,
							   Model model) {
		model.addAttribute("client", client);
		System.out.println(client);
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
