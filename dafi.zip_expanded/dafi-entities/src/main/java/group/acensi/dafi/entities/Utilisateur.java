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
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Represente un utilisateur qui peut se loguer
 *
 * @author Nadeem Nayeck <nadeem.nayeck@acensi.group>
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Utilisateur extends AbstractEntity {
	
	@Column(unique = true)
	private String username;
	
	private String password;
	
	private boolean enabled = true;
	
	private boolean accountNonExpired = true;
	
	private boolean accountNonLocked = true;
	
	private boolean credentialsNonExpired = false;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private Role role;
	
	/**
	 * On peut creer plusieurs comptes de login pour le meme employe/collaborateur 
	 */
	@ManyToOne
	@JoinColumn(name = "collaborateur_id")
	private Collaborateur collaborateur;

	@ManyToMany
	@JoinTable(name = "order", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "menu_id") })
	private List<Menu> menu;


}
