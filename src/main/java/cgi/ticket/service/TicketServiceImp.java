package cgi.ticket.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cgi.ticket.entity.Ticket;
import cgi.ticket.exception.NotFoundException;
import cgi.ticket.repository.TicketRepository;
import cgi.ticket.utils.TicketStatut;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class TicketServiceImp implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	@Override
	public List<Ticket> afficherTickets() {
		log.info("afficherTickets() {}");
		List<Ticket> tickets = ticketRepository.findAll();
		log.info("afficherTickets() {} -end-", tickets);
		return tickets;
	}

	@Override
	public Optional<Ticket> afficherTicketParId(Long id) throws NotFoundException {
		Optional<Ticket> ticket = ticketRepository.findById(id);
		if (!ticket.isPresent())
			throw new NotFoundException("l'identifiant " + id + " est introvable");
		return ticket;
	}

	@Override
	public Ticket afficherTicketParNomEtStatut(String nom, TicketStatut statut) throws NotFoundException {
		Optional<Ticket> ticket = ticketRepository.findByNomAndStatut(nom, statut);
		if (!ticket.isPresent())
			throw new NotFoundException("le nom " + nom + "est" + statut + "un introvable");
		return ticket.get();
	}

	@Override
	public Ticket saveTicket(Ticket ticket) throws NotFoundException {
		if (ticket == null)
			throw new NotFoundException("le ticket " + ticket + " ne doit pas etre null");
		return ticketRepository.save(ticket);
	}

	@Override
	public Boolean deleteTicket(Long id) throws NotFoundException {
		if (id == null)
			throw new NotFoundException("l'identifiant " + id + " ne doit pas etre null");
		Optional<Ticket> ticket = ticketRepository.findById(id);
		if (!ticket.isPresent())
			throw new NotFoundException("le ticket " + ticket.get() + " est intouvable");
		ticketRepository.deleteById(id);
		return true;

	}

	@Override
	public Ticket modifierTicket(Ticket ticket, Long id) throws NotFoundException {
		if (id == null && ticket == null)
			throw new NotFoundException("l'identifiant " + id + " ne doit pas etre null");
		
		Optional<Ticket> ticketToUpdate = ticketRepository.findById(id);
		
		if (!ticketToUpdate.isPresent())
			throw new NotFoundException("le ticket " + ticketToUpdate.get() + " est intouvable");
		
		ticketToUpdate.get().setCriticite(ticket.getCriticite());
		ticketToUpdate.get().setDateProblematique(ticket.getDateProblematique());
		ticketToUpdate.get().setNom(ticket.getNom());
		ticketToUpdate.get().setNumero(ticket.getNumero());
		ticketToUpdate.get().setPriorete(ticket.getPriorete());
		ticketToUpdate.get().setStatut(ticket.getStatut());
		return ticketRepository.saveAndFlush(ticketToUpdate.get());
	}

}
