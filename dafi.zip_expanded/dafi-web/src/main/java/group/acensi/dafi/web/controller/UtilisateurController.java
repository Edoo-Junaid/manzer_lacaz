/*
 * Copyright (C) Acensi Group
 * 
 * All Rights Reserved Unauthorized copying of this file,
 * via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 */
package group.acensi.dafi.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import group.acensi.dafi.service.api.UtilisateurService;
import group.acensi.dafi.service.dto.UtilisateurDto;

/**
 * Controller for creating/updating employees/collaborators
 *
 * @author Nadeem Nayeck <nadeem.nayeck@acensi.group>
 *
 */
@RestController
@RequestMapping("/api")
public class UtilisateurController {
	
	@Autowired
	private UtilisateurService utilisateurService;

	@GetMapping("/listUtilisateurs")
	public List<UtilisateurDto> listUtilisateurs() {
		return this.utilisateurService.findAllUtilisateurs();
	}
	
	
	
}
