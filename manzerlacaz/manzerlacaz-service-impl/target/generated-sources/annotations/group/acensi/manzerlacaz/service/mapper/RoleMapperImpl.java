package group.acensi.manzerlacaz.service.mapper;

import group.acensi.manzerlacaz.entities.Role;
import group.acensi.manzerlacaz.service.dto.RoleDto;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-11T11:32:58+0400",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 1.4.100.v20220318-0906, environment: Java 17.0.4.1 (Eclipse Adoptium)"
)
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleDto toDto(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setNom( role.getNom() );

        return roleDto;
    }

    @Override
    public Role toEntity(RoleDto roleDto) {
        if ( roleDto == null ) {
            return null;
        }

        Role role = new Role();

        role.setNom( roleDto.getNom() );

        return role;
    }
}
