/*
 * Copyright (C) Acensi Group
 * 
 * All Rights Reserved Unauthorized copying of this file,
 * via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 */
package group.acensi.manzerlacaz.service.api;

import java.util.List;

import group.acensi.manzerlacaz.service.dto.UtilisateurDto;
import group.acensi.manzerlacaz.service.exception.UserNotfoundException;

/**
 *
 *
 * @author Nadeem Nayeck <nadeem.nayeck@acensi.group>
 *
 */
public interface UtilisateurService {

	/**
	 * recupere un utilisateur par son username
	 * 
	 * @param username
	 * @return
	 * @throws UserNotfoundException
	 */
	public UtilisateurDto findUtilisateur(String username);
	
	
	public Long createUtilisateur(UtilisateurDto dto);
	
	/**
	 * recupere tout les utilisateurs
	 * 
	 * @param username
	 * @return
	 * @throws UserNotfoundException
	 */
	public List<UtilisateurDto> findAllUtilisateurs();
		

}
