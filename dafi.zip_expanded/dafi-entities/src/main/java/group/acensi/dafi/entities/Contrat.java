/*
 * Copyright (C) Acensi Group
 * 
 * All Rights Reserved Unauthorized copying of this file,
 * via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 */

package group.acensi.dafi.entities;

import java.time.LocalDate;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import group.acensi.dafi.entities.base.AbstractEntity;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKey;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Represente un contrat
 *
 * @author Nadeem Nayeck <nadeem.nayeck@acensi.group>
 * 
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Contrat extends AbstractEntity {

	@Temporal(TemporalType.DATE)
	private LocalDate debut;

	@Temporal(TemporalType.DATE)
	private LocalDate fin;


	private String typeRecrutementRh;

	@ManyToOne
	@JoinColumn(name = "bu_recrutement_id")
	private BusinessUnit buRecrutement;

	@ManyToOne
	@JoinColumn(name = "rh_recruteur_id")
	private Collaborateur rhRecrutement;

	@ManyToOne
	@JoinColumn(name = "manager_id")
	private Collaborateur manager;

	private Integer renumeration;

	private Integer heuresHebdo;
	
	private String domiciliationBancaire;
	
	private String codeBic;
	
	private String iban;

}
