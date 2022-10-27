package group.acensi.manzerlacaz.service.mapper;

import group.acensi.manzerlacaz.entities.Role;
import group.acensi.manzerlacaz.entities.Utilisateur;
import group.acensi.manzerlacaz.service.dto.RoleDto;
import group.acensi.manzerlacaz.service.dto.UtilisateurDto;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-27T08:45:07+0400",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 1.4.100.v20220318-0906, environment: Java 17.0.3 (Eclipse Adoptium)"
)
public class UtilisateurMapperImpl implements UtilisateurMapper {

    @Override
    public Utilisateur toEntity(UtilisateurDto dto) {
        if ( dto == null ) {
            return null;
        }

        Utilisateur utilisateur = new Utilisateur();

        utilisateur.setCreated( dto.getCreated() );
        utilisateur.setId( dto.getId() );
        utilisateur.setLastModified( dto.getLastModified() );
        utilisateur.setAccountNonExpired( dto.isAccountNonExpired() );
        utilisateur.setAccountNonLocked( dto.isAccountNonLocked() );
        utilisateur.setCredentialsNonExpired( dto.isCredentialsNonExpired() );
        utilisateur.setEnabled( dto.isEnabled() );
        utilisateur.setPassword( dto.getPassword() );
        utilisateur.setRole( roleDtoToRole( dto.getRole() ) );
        utilisateur.setUsername( dto.getUsername() );

        return utilisateur;
    }

    @Override
    public UtilisateurDto toDto(Utilisateur findbyUsername) {
        if ( findbyUsername == null ) {
            return null;
        }

        UtilisateurDto utilisateurDto = new UtilisateurDto();

        utilisateurDto.setAccountNonExpired( findbyUsername.isAccountNonExpired() );
        utilisateurDto.setAccountNonLocked( findbyUsername.isAccountNonLocked() );
        utilisateurDto.setCreated( findbyUsername.getCreated() );
        utilisateurDto.setCredentialsNonExpired( findbyUsername.isCredentialsNonExpired() );
        utilisateurDto.setEnabled( findbyUsername.isEnabled() );
        utilisateurDto.setId( findbyUsername.getId() );
        utilisateurDto.setLastModified( findbyUsername.getLastModified() );
        utilisateurDto.setPassword( findbyUsername.getPassword() );
        utilisateurDto.setRole( roleToRoleDto( findbyUsername.getRole() ) );
        utilisateurDto.setUsername( findbyUsername.getUsername() );

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
