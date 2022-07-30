package com.example.springProject.controllers;

import com.example.springProject.services.ClientServiceImpl;
import com.example.springProject.entities.Client;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/client")
public class ClientController {

	private final ClientServiceImpl clientService;

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

	@PostMapping("update")
	public ResponseEntity<Client> updateClient(@RequestBody Client c) {
		log.info("POST | c.id: {} | c.firstname: {} | c.lastname: {}", c.getId(), c.getFirstname(), c.getLastname());
		return ResponseEntity.ok(clientService.updateClient(c.getId(), c.getFirstname(), c.getLastname()));
	}

	@PostMapping("/client_deleted")
	public String client(@ModelAttribute Client client, Model model) {
		clientService.deleteClientById(client.getId());
		return "html/client_deleted";
	}


	@RequestMapping(value = "/count_clients", method = RequestMethod.GET
			, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Long> countClients() {
		return clientService.countClients();
	}

	@RequestMapping(value = "/get_all", method = RequestMethod.GET)
//			produces = MediaType.APPLICATION_JSON_VALUE)
//			produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<List<Client>> getAll() {
		log.info("Incoming request to allClients!");
		return clientService.getAll();
	}
}
