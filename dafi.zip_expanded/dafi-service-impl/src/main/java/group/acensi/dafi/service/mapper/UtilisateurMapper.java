/*
 * Copyright (C) Acensi Group
 * 
 * All Rights Reserved Unauthorized copying of this file,
 * via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 */
package group.acensi.dafi.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import group.acensi.dafi.entities.Droit;
import group.acensi.dafi.entities.Utilisateur;
import group.acensi.dafi.service.dto.UtilisateurDto;

/**
 *
 *
 * @author Nadeem Nayeck <nadeem.nayeck@acensi.group>
 *
 */
@org.mapstruct.Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UtilisateurMapper {

	UtilisateurMapper INSTANCE = Mappers.getMapper(UtilisateurMapper.class);

	Utilisateur toEntity(UtilisateurDto dto);

	default List<Droit> mapDroitNom(List<String> droits) {
       List<Droit> droitEntities = new ArrayList<>();
       if (droits != null) {
    	   for(String droit: droits) {
    		   Droit droitEntity = new Droit();
    		   droitEntity.setNom(droit);
    		   droitEntities.add(droitEntity);
    	   }
       }
       return droitEntities;
    }

	UtilisateurDto toDto(Utilisateur findbyUsername);
	
	default List<String> mapDroitEntity(List<Droit> droitEntities) {
		List<String> droitNoms = new ArrayList<>();
		if (droitEntities != null) {
			for(Droit droit: droitEntities) {
				droitNoms.add(droit.getNom());
			}
		}
		return droitNoms;
	}

}
