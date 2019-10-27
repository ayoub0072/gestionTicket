package cgi.ticket.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import cgi.ticket.audit.Auditable;
import cgi.ticket.utils.TicketStatut;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="TICKETS")
public class Ticket extends Auditable<String>  implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Min(10)
	private Integer criticite;
	
	@NotNull(message="ce champs est obligatoire")
	private String nom;
	
	@NotNull(message="ce champs est obligatoire")
	private Integer numero;
	
	private String fichierNom;
	
	@Lob
	private byte[] fichier;
	
	@NotNull(message="ce champs est obligatoire")
	private Integer priorete;
	
	@Enumerated(EnumType.STRING)
	private TicketStatut statut;
	
	@Temporal(TemporalType.DATE)
	private Date dateProblematique;
}
