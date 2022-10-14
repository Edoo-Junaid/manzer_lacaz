/*
 * Copyright (C) Acensi Group
 * 
 * All Rights Reserved Unauthorized copying of this file,
 * via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 */

package group.acensi.dafi.entities;

import java.util.List;

import group.acensi.dafi.entities.base.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
public class Droit extends AbstractEntity {
	
	private String nom;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "role_droit", joinColumns = { @JoinColumn(name = "droit_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private List<Role> role;

}
