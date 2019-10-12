package cgi.ticket.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cgi.ticket.entity.Ticket;
import cgi.ticket.exception.NotFoundException;
import cgi.ticket.service.TicketServiceImp;
import cgi.ticket.utils.TicketStatut;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api")
public class TicketController {

	@Autowired
	private TicketServiceImp ticketServiceImp;

	@GetMapping(value = "/tickets")
	public ResponseEntity<List<Ticket>> afficherTickets() {
		List<Ticket> tickets = ticketServiceImp.afficherTickets();
		if (tickets.isEmpty()) {
			new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(tickets, HttpStatus.OK);
	}

	@GetMapping(value = "/tickets/{id}")
	public ResponseEntity<Ticket> afficherTicketParId(@PathVariable Long id) throws NotFoundException {
		Optional<Ticket> tickets = ticketServiceImp.afficherTicketParId(id);
		return new ResponseEntity<>(tickets.get(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/tickets/search")
	public ResponseEntity<Ticket> afficherTicketParNomEtStatut(@RequestParam String nom,@RequestParam TicketStatut statut) throws NotFoundException {
		Ticket tickets = ticketServiceImp.afficherTicketParNomEtStatut(nom, statut);
		if (tickets == null) {
			new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(tickets, HttpStatus.OK);
	}
	
	@PostMapping(value="/tickets")
	public ResponseEntity<Ticket> saveTicket(@RequestBody Ticket ticket) throws NotFoundException{
		Ticket tickets =  ticketServiceImp.saveTicket(ticket);
		return new ResponseEntity<>(tickets, HttpStatus.CREATED);
	}
	
	@DeleteMapping(value="/tickets/{id}")
	public ResponseEntity<Boolean> deleteTicket(@PathVariable Long id) throws NotFoundException{
		ticketServiceImp.deleteTicket(id);
		return new ResponseEntity<>(true,HttpStatus.NO_CONTENT);
	}
	
	@PutMapping(value="/tickets/{id}")
	public ResponseEntity<Boolean> deleteTicket(@RequestBody Ticket ticket,@PathVariable Long id) throws NotFoundException{
		ticketServiceImp.modifierTicket(ticket, id);
		return new ResponseEntity<>(true,HttpStatus.ACCEPTED);
	}
}
