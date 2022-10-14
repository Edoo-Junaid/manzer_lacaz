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

import group.acensi.dafi.entities.base.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Represente un droit ou un habilitation d'un utilisateur de la plateforme
 *
 * @author Nadeem Nayeck <nadeem.nayeck@acensi.group>
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class PeriodeEssai extends AbstractEntity implements Comparable<LocalDate> {
	
	@Temporal(TemporalType.DATE)
	private LocalDate debut;
	
	@Temporal(TemporalType.DATE)
	private LocalDate fin;
	
	@ManyToOne
	@JoinColumn(name = "contrat_id")
	private Contrat contrat;

	@Override
	public int compareTo(LocalDate o) {
		return this.getDebut().compareTo(o);
	}

}
