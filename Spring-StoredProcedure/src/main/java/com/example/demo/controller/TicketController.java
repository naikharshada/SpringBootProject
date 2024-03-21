package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Ticket;
import com.example.demo.repository.TicketRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@RestController
public class TicketController {

	@Autowired
	private TicketRepository ticketRepository;
	
	@PersistenceContext
	private EntityManager em;
	
	@PostMapping("/saveTicket")
	@Transactional
	public ResponseEntity<String> saveTicket(@RequestBody Ticket ticket) {
		em.persist(ticket);
		return ResponseEntity.ok("Ticket saved successfully");
	}
	
	@GetMapping("/findTickets")
	public List<Ticket> findTickets() {
		return ticketRepository.getTicketInfo();
	}
	
	@GetMapping("/findTicketsByCategory/{category}")
	public List<Ticket> findTicketByCategory(@PathVariable String category ) {
		return ticketRepository.getTicketInfoByCategory(category);
	}
}