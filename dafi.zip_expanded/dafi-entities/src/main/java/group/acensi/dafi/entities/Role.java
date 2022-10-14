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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Represente un group qui peut avoir plusieur profils qui peut se loguer
 *
 * @author Nadeem Nayeck <nadeem.nayeck@acensi.group>
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Role extends AbstractEntity {
	
	private String nom;
	
	@ManyToMany
	@JoinTable(name = "role_droit", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = {
			@JoinColumn(name = "droit_id") })
	private List<Droit> droits;
	
}
