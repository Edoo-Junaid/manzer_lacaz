/*
 * Copyright (C) Acensi Group
 * 
 * All Rights Reserved Unauthorized copying of this file,
 * via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 */
package group.acensi.manzerlacaz.service.mapper;

<<<<<<< HEAD
import org.mapstruct.ReportingPolicy;
=======
>>>>>>> f2bbc8c5a1d55269167190a3ba5fefab28e50b64
import org.mapstruct.factory.Mappers;

import group.acensi.manzerlacaz.entities.Role;
import group.acensi.manzerlacaz.service.dto.RoleDto;

/**
 *
 *
 * @author Nadeem Nayeck <nadeem.nayeck@acensi.group>
 *
 */
@org.mapstruct.Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDto toDto(Role role);

    Role toEntity(RoleDto roleDto);

}
