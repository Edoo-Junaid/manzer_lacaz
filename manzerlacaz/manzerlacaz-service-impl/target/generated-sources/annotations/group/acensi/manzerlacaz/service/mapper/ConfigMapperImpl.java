package group.acensi.manzerlacaz.service.mapper;

import group.acensi.manzerlacaz.entities.Config;
import group.acensi.manzerlacaz.service.dto.ConfigDto;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-12T12:33:34+0400",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 1.4.200.v20221012-0724, environment: Java 17.0.5 (Eclipse Adoptium)"
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
