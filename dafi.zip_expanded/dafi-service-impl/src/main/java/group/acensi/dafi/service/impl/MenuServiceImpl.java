package group.acensi.dafi.service.impl;

import group.acensi.dafi.dao.MenuRepository;
import group.acensi.dafi.entities.Menu;
import group.acensi.dafi.service.api.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group.acensi.dafi.dao.RoleRepository;
import group.acensi.dafi.dao.UtilisateurRepository;
import group.acensi.dafi.entities.Role;
import group.acensi.dafi.entities.Utilisateur;
import group.acensi.dafi.service.api.UtilisateurService;
import group.acensi.dafi.service.dto.UtilisateurDto;
import group.acensi.dafi.service.mapper.UtilisateurMapper;
@Service
public class MenuServiceImpl implements MenuService{
    
    @Autowired
    private MenuRepository menurepository;
    
    @Override
    public void createMenu(Menu menu) {
        menurepository.save(menu);
    }
    
}
