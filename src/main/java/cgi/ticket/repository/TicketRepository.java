package cgi.ticket.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cgi.ticket.entity.Ticket;
import cgi.ticket.utils.TicketStatut;

public interface TicketRepository extends JpaRepository<Ticket, Long>{

	public Optional<Ticket> findByNomAndStatut(String nom,TicketStatut statut);
}
