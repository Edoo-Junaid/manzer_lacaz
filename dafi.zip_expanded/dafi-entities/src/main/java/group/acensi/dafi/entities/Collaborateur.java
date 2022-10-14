/*
 * Copyright (C) Acensi Group
 * 
 * All Rights Reserved Unauthorized copying of this file,
 * via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 */

package group.acensi.dafi.entities;

import java.util.Map;

import group.acensi.dafi.entities.base.AbstractEntity;
import group.acensi.dafi.entities.parametrage.ParametrageCollaborateur;
import group.acensi.dafi.entities.types.Adresse;
import group.acensi.dafi.entities.types.Civilite;
import group.acensi.dafi.entities.types.Email;
import group.acensi.dafi.entities.types.Naissance;
import group.acensi.dafi.entities.types.Telephone;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MapKey;
import jakarta.persistence.OneToMany;
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
public class Collaborateur extends AbstractEntity {
	
	@Enumerated(EnumType.STRING)
	private Civilite civilite;
	
	private String nom;
	
	private String prenoms;
	
	private String nomMarital;
	
	@OneToMany(mappedBy = "collaborateur")
	@ElementCollection
    @MapKey(name = "cle")
    private Map<String, ParametrageCollaborateur> parametrage;
    
    @Embedded
    private Naissance naissance;
    
    @Embedded
    private Telephone telephone;
    
    @Embedded
    private Email email;
    
    @Embedded
    private Adresse adresse;  
    

}
