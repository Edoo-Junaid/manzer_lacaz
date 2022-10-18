package group.acensi.dafi.service.impl;

import group.acensi.dafi.dao.MenuRepository;
import group.acensi.dafi.entities.Menu;
import group.acensi.dafi.service.api.MenuService;
import group.acensi.dafi.service.dto.MenuDto;
import group.acensi.dafi.service.mapper.MenuMapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MenuServiceImpl implements MenuService{
    
    @Autowired
    private MenuRepository menurepository;

    @Override
    public Menu createMenu(MenuDto menuDto) {
        Menu menu = MenuMapper.INSTANCE.toEntity(menuDto);
          return menurepository.save(menu);
    }

    @Override
    public List<Menu> listAllMenu() {
       return  menurepository.findAll();
    }
    
}
