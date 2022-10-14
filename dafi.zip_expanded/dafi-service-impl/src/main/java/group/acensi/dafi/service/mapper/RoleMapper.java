/*
 * Copyright (C) Acensi Group
 * 
 * All Rights Reserved Unauthorized copying of this file,
 * via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 */
package group.acensi.dafi.service.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import group.acensi.dafi.entities.Droit;
import group.acensi.dafi.entities.Role;
import group.acensi.dafi.service.dto.RoleDto;

/**
 *
 *
 * @author Nadeem Nayeck <nadeem.nayeck@acensi.group>
 *
 */
@org.mapstruct.Mapper
public interface RoleMapper {
	
	RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

	@Mapping(target = "droits", source = "droits")
	RoleDto toDto(Role role);
	
	default String mapDroitToNomDroit(Droit droit) {
        return droit.getNom();
    }

}
