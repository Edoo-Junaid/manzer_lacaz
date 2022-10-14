/*
 * Copyright (C) Acensi Group
 * 
 * All Rights Reserved Unauthorized copying of this file,
 * via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 */
package group.acensi.dafi.service.mapper;

import java.util.List;

import org.mapstruct.factory.Mappers;

import group.acensi.dafi.entities.BusinessUnit;
import group.acensi.dafi.service.dto.BusinessUnitDto;

/**
 *
 *
 * @author Nadeem Nayeck <nadeem.nayeck@acensi.group>
 *
 */
@org.mapstruct.Mapper
public interface BusinessUnitMapper {

	BusinessUnitMapper INSTANCE = Mappers.getMapper(BusinessUnitMapper.class);

	BusinessUnitDto toDto(BusinessUnit businessUnit);

	List<BusinessUnitDto> toDtos(List<BusinessUnit> employees);

}
