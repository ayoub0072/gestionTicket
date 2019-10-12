package cgi.ticket.service;

import java.util.List;
import java.util.Optional;

import cgi.ticket.entity.Ticket;
import cgi.ticket.exception.NotFoundException;
import cgi.ticket.utils.TicketStatut;

public interface TicketService {
	
	List<Ticket> afficherTickets();
	
	Optional<Ticket> afficherTicketParId(Long id) throws NotFoundException;
	
	Ticket afficherTicketParNomEtStatut(String nom,TicketStatut statut) throws NotFoundException;
	
	Ticket saveTicket(Ticket ticket) throws NotFoundException;
	
	Ticket modifierTicket(Ticket ticket,Long id) throws NotFoundException;
	
	Boolean deleteTicket(Long id) throws NotFoundException;
}
