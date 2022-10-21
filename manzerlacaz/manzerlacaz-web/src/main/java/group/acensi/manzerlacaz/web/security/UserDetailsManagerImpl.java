/*
 * Copyright (C) Acensi Group
 * 
 * All Rights Reserved Unauthorized copying of this file,
 * via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 */
package group.acensi.manzerlacaz.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import group.acensi.manzerlacaz.service.api.UtilisateurService;
import group.acensi.manzerlacaz.service.dto.UtilisateurDto;
import group.acensi.manzerlacaz.web.mapper.UserDetailsMapper;

/**
 *
 *
 * @author Nadeem Nayeck <nadeem.nayeck@acensi.group>
 *
 */
@Component
public class UserDetailsManagerImpl implements UserDetailsService {
	
	@Autowired
	private UtilisateurService utilisateurService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UtilisateurDto utilisateurDto = this.utilisateurService.findUtilisateur(username);
		if (utilisateurDto == null) {
			throw new UsernameNotFoundException("Username "+username+" does not exist");
		}
		return UserDetailsMapper.INSTANCE.toModel(utilisateurDto);
		
	}



}
