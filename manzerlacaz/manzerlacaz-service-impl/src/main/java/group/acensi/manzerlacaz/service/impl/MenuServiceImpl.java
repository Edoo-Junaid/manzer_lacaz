package group.acensi.manzerlacaz.service.impl;

import group.acensi.manzerlacaz.dao.MenuRepository;
import group.acensi.manzerlacaz.entities.Menu;
import group.acensi.manzerlacaz.service.api.MenuService;
import group.acensi.manzerlacaz.service.dto.MenuDto;
import group.acensi.manzerlacaz.service.mapper.MenuMapper;
import group.acensi.manzerlacaz.service.mapper.UtilisateurMapper;

import java.util.Calendar;
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
        menu.setWeekNum(this.getCurrentWeekNumber());
        return menurepository.save(menu);
    }

    
    @Override
    public List<MenuDto> listAllMenu() {
       return  menurepository.findAll().stream().map(MenuMapper.INSTANCE::toDto).toList();
    }


    @Override
    public int getCurrentWeekNumber() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.WEEK_OF_YEAR);
    }


    @Override
    public List<MenuDto> listCurrentMenu() {
        return menurepository.findByWeekNum(this.getCurrentWeekNumber()).stream().map(MenuMapper.INSTANCE::toDto).toList();
        
    }
    
    
    
    
}
