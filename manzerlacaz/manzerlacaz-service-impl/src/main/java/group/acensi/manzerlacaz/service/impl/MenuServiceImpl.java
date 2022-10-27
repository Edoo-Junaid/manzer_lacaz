package group.acensi.manzerlacaz.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group.acensi.manzerlacaz.dao.MenuRepository;
import group.acensi.manzerlacaz.entities.Menu;
import group.acensi.manzerlacaz.service.api.MenuService;
import group.acensi.manzerlacaz.service.dto.MenuDto;
import group.acensi.manzerlacaz.service.mapper.MenuMapper;


@Service
public class MenuServiceImpl implements MenuService{
    
    @Autowired
    private MenuRepository menurepository;

    @Override
    public Menu createMenu(MenuDto menuDto) {
        Menu menu = MenuMapper.INSTANCE.toEntity(menuDto);
        menu.setWeekNum(this.getCurrentWeekNumber());
        if(checkIfMenuExists(menu.getDay())){
            menurepository.deleteById(this.getIdFromDayAndWeekNum(menu.getDay()));
           System.out.println("I am inside delete");
            return menurepository.save(menu);
        }else{
            System.out.println("I am inside create");
            return menurepository.save(menu);
        }
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

    @Override
    public boolean checkIfMenuExists(String day) {
        return menurepository.checkIfMenuExists(this.getCurrentWeekNumber(), day);
    }

    @Override
    public Long getIdFromDayAndWeekNum(String day) {
        return menurepository.getIdFromDayAndWeekNum(this.getCurrentWeekNumber(), day);
    }
    
}
