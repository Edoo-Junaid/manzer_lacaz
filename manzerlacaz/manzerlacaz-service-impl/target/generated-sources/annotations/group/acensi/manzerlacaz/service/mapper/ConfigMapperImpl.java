package group.acensi.manzerlacaz.service.mapper;

import group.acensi.manzerlacaz.entities.Config;
import group.acensi.manzerlacaz.service.dto.ConfigDto;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-31T22:46:01+0400",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 1.4.100.v20220318-0906, environment: Java 17.0.4.1 (Eclipse Adoptium)"
)
public class ConfigMapperImpl implements ConfigMapper {

    @Override
    public Config toEntity(ConfigDto dto) {
        if ( dto == null ) {
            return null;
        }

        Config config = new Config();

        config.setCreated( dto.getCreated() );
        config.setId( dto.getId() );
        config.setLastModified( dto.getLastModified() );
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

        configDto.setCreated( config.getCreated() );
        configDto.setId( config.getId() );
        configDto.setLastModified( config.getLastModified() );
        configDto.setName( config.getName() );
        configDto.setValue( config.getValue() );

        return configDto;
    }
}
