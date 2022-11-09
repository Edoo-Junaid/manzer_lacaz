package group.acensi.manzerlacaz.service.mapper;

import group.acensi.manzerlacaz.entities.Config;
import group.acensi.manzerlacaz.service.dto.ConfigDto;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-09T11:00:58+0400",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.4.1 (Eclipse Adoptium)"
)
public class ConfigMapperImpl implements ConfigMapper {

    @Override
    public Config toEntity(ConfigDto dto) {
        if ( dto == null ) {
            return null;
        }

        Config config = new Config();

        config.setId( dto.getId() );
        config.setLastModified( dto.getLastModified() );
        config.setCreated( dto.getCreated() );
        config.setName( dto.getName() );
        config.setValue( dto.getValue() );

        return config;
    }

    @Override
    public ConfigDto toDto(Config config) {
        if ( config == null ) {
            return null;
        }

        ConfigDto configDto = new ConfigDto();

        configDto.setId( config.getId() );
        configDto.setName( config.getName() );
        configDto.setValue( config.getValue() );
        configDto.setLastModified( config.getLastModified() );
        configDto.setCreated( config.getCreated() );

        return configDto;
    }
}
