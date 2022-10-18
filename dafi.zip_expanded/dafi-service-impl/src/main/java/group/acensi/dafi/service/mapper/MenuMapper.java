package group.acensi.dafi.service.mapper;

import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import group.acensi.dafi.entities.Menu;
import group.acensi.dafi.service.dto.MenuDto;

@org.mapstruct.Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuMapper {
    MenuMapper INSTANCE = Mappers.getMapper(MenuMapper.class);
    Menu toEntity(MenuDto dto);
    MenuDto toDto(Menu menu);
    
}
