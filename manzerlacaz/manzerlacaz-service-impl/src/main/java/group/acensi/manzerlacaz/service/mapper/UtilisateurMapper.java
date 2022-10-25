/*
 * Copyright (C) Acensi Group
 * 
 * All Rights Reserved Unauthorized copying of this file,
 * via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 */
package group.acensi.manzerlacaz.service.mapper;

import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import group.acensi.manzerlacaz.entities.Utilisateur;
import group.acensi.manzerlacaz.service.dto.UtilisateurDto;

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



	UtilisateurDto toDto(Utilisateur findbyUsername);
	

}
