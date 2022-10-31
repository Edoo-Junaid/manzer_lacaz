/*
 * Copyright (C) Acensi Group
 * 
 * All Rights Reserved Unauthorized copying of this file,
 * via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 */
package group.acensi.manzerlacaz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group.acensi.manzerlacaz.dao.RoleRepository;
import group.acensi.manzerlacaz.dao.UtilisateurRepository;
import group.acensi.manzerlacaz.entities.Role;
import group.acensi.manzerlacaz.entities.Utilisateur;
import group.acensi.manzerlacaz.service.api.UtilisateurService;
import group.acensi.manzerlacaz.service.dto.UtilisateurDto;
import group.acensi.manzerlacaz.service.mapper.UtilisateurMapper;

/**
 *
 *
 * @author Nadeem Nayeck <nadeem.nayeck@acensi.group>
 *
 */

@Service
public class UtilisateurServiceImpl implements UtilisateurService {
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public UtilisateurDto findUtilisateur(String username) {
		return UtilisateurMapper.INSTANCE.toDto(this.utilisateurRepository.findbyUsername(username));
	}

	@Override
	public Long createUtilisateur(UtilisateurDto dto) {
		Utilisateur utilisateur = UtilisateurMapper.INSTANCE.toEntity(dto);
		Role role = roleRepository.findbyNom(dto.getRole().getNom());
		utilisateur.setRole(role);
		utilisateur = this.utilisateurRepository.save(utilisateur);
		return utilisateur.getId();
	}

	@Override
	public List<UtilisateurDto> findAllUtilisateurs() {
		return this.utilisateurRepository.findAll().stream().map(UtilisateurMapper.INSTANCE::toDto).toList();
	}

}
