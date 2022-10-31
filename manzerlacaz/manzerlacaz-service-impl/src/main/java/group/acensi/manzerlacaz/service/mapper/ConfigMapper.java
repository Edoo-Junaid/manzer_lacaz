package group.acensi.manzerlacaz.service.mapper;

import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import group.acensi.manzerlacaz.entities.Config;
import group.acensi.manzerlacaz.service.dto.ConfigDto;

@org.mapstruct.Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ConfigMapper {
    ConfigMapper INSTANCE = Mappers.getMapper(ConfigMapper.class);
    Config toEntity(ConfigDto dto);
    ConfigDto toDto(Config config);
    
}
