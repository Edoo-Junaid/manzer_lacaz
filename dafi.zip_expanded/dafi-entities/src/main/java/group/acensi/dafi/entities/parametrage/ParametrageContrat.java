/*
 * Copyright (C) Acensi Group
 * 
 * All Rights Reserved Unauthorized copying of this file,
 * via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 */

package group.acensi.dafi.entities.parametrage;

import group.acensi.dafi.entities.Contrat;
import group.acensi.dafi.entities.base.AbstractKeyValueEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Represente un 'employé' ou autre collaborateur (consultant, freelance, etc)
 *
 * @author Nadeem Nayeck <nadeem.nayeck@acensi.group>
 * 
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class ParametrageContrat extends AbstractKeyValueEntity {

	@ManyToOne
	@JoinColumn(name = "contrat_id")
	private Contrat contrat;

}
