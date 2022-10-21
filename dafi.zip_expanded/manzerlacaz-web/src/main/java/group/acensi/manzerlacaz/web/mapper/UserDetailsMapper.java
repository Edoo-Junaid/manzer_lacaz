/*
 * Copyright (C) Acensi Group
 * 
 * All Rights Reserved Unauthorized copying of this file,
 * via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 */
package group.acensi.manzerlacaz.web.mapper;

import org.mapstruct.factory.Mappers;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import group.acensi.manzerlacaz.service.dto.UtilisateurDto;
import group.acensi.manzerlacaz.web.security.UserDetailsImpl;

/**
 *
 *
 * @author Nadeem Nayeck <nadeem.nayeck@acensi.group>
 *
 */
@org.mapstruct.Mapper
public interface UserDetailsMapper {

	UserDetailsMapper INSTANCE = Mappers.getMapper(UserDetailsMapper.class);

	default UserDetails toModel(UtilisateurDto dto) {

		UserDetailsImpl userDetails = new UserDetailsImpl();
		userDetails.setAccountNonExpired(dto.isAccountNonExpired());
		userDetails.setAccountNonLocked(userDetails.isAccountNonLocked());
		userDetails.setId(dto.getId());
		if (dto.getRole() != null ){//&& dto.getRole().getDroits() != null) {
			
			userDetails.setRole(dto.getRole().getNom());
			
		}
		
		userDetails.setCredentialsNonExpired(dto.isCredentialsNonExpired());
		userDetails.setEnabled(dto.isEnabled());
		userDetails.setPassword(dto.getPassword());
		userDetails.setUsername(dto.getUsername());

		return userDetails;
	}
	
}
