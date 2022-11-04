package group.acensi.manzerlacaz.service.mapper;

import group.acensi.manzerlacaz.entities.Role;
import group.acensi.manzerlacaz.entities.Utilisateur;
import group.acensi.manzerlacaz.service.dto.RoleDto;
import group.acensi.manzerlacaz.service.dto.UtilisateurDto;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-04T14:51:57+0400",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.4.1 (Eclipse Adoptium)"
)
public class UtilisateurMapperImpl implements UtilisateurMapper {

    @Override
    public Utilisateur toEntity(UtilisateurDto dto) {
        if ( dto == null ) {
            return null;
        }

        Utilisateur utilisateur = new Utilisateur();

        utilisateur.setId( dto.getId() );
        utilisateur.setLastModified( dto.getLastModified() );
        utilisateur.setCreated( dto.getCreated() );
        utilisateur.setUsername( dto.getUsername() );
        utilisateur.setPassword( dto.getPassword() );
        utilisateur.setEnabled( dto.isEnabled() );
        utilisateur.setAccountNonExpired( dto.isAccountNonExpired() );
        utilisateur.setAccountNonLocked( dto.isAccountNonLocked() );
        utilisateur.setCredentialsNonExpired( dto.isCredentialsNonExpired() );
        utilisateur.setRole( roleDtoToRole( dto.getRole() ) );

        return utilisateur;
    }

    @Override
    public UtilisateurDto toDto(Utilisateur findbyUsername) {
        if ( findbyUsername == null ) {
            return null;
        }

        UtilisateurDto utilisateurDto = new UtilisateurDto();

        utilisateurDto.setId( findbyUsername.getId() );
        utilisateurDto.setRole( roleToRoleDto( findbyUsername.getRole() ) );
        utilisateurDto.setPassword( findbyUsername.getPassword() );
        utilisateurDto.setUsername( findbyUsername.getUsername() );
        utilisateurDto.setAccountNonExpired( findbyUsername.isAccountNonExpired() );
        utilisateurDto.setAccountNonLocked( findbyUsername.isAccountNonLocked() );
        utilisateurDto.setCredentialsNonExpired( findbyUsername.isCredentialsNonExpired() );
        utilisateurDto.setEnabled( findbyUsername.isEnabled() );
        utilisateurDto.setLastModified( findbyUsername.getLastModified() );
        utilisateurDto.setCreated( findbyUsername.getCreated() );

        return utilisateurDto;
    }

    protected Role roleDtoToRole(RoleDto roleDto) {
        if ( roleDto == null ) {
            return null;
        }

        Role role = new Role();

        role.setNom( roleDto.getNom() );

        return role;
    }

    protected RoleDto roleToRoleDto(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setNom( role.getNom() );

        return roleDto;
    }
}
