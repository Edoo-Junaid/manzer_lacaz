/*
 * Copyright (C) Acensi Group
 * 
 * All Rights Reserved Unauthorized copying of this file,
 * via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 */
package group.acensi.manzerlacaz.service.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import group.acensi.manzerlacaz.entities.Role;
import group.acensi.manzerlacaz.service.dto.RoleDto;

/**
 *
 *
 * @author Nadeem Nayeck <nadeem.nayeck@acensi.group>
 *
 */
@org.mapstruct.Mapper
public interface RoleMapper {
	
	RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);


	RoleDto toDto(Role role);


    Role toEntity(RoleDto roleDto);
	

}
