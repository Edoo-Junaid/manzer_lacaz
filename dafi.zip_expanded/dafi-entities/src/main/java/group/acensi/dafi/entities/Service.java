/*
 * Copyright (C) Acensi Group
 * 
 * All Rights Reserved Unauthorized copying of this file,
 * via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 */

package group.acensi.dafi.entities;

import group.acensi.dafi.entities.base.AbstractEntity;
import jakarta.persistence.Entity;
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
public class Service extends AbstractEntity {
	
	private String nom;
	
}
