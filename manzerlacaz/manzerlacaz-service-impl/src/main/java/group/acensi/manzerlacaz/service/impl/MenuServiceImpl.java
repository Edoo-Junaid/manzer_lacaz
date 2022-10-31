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


    //Method to create a menu
    //Check if a menu already exists for the same day and week number
    //If it exists, update it
    //Else create a new menu
    @Override
    public Menu createMenu(MenuDto menuDto) {
        menuDto.setWeekNum(this.getCurrentWeekNumber());
        if(checkIfMenuExists(menuDto.getDay())){
            menuDto.setId(this.getIdFromDayAndWeekNum(menuDto.getDay()));
        }
        Menu menu = MenuMapper.INSTANCE.toEntity(menuDto);
        return menurepository.save(menu);
    }

    //Method to list all menus
    @Override
    public List<MenuDto> listAllMenu() {
       return  menurepository.findAll().stream().map(MenuMapper.INSTANCE::toDto).toList();
    }

    //Method to get the current week number
    @Override
    public int getCurrentWeekNumber() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.WEEK_OF_YEAR);
    }
    
    //Method to list the current menu
    @Override
    public List<MenuDto> listCurrentMenu() {
        return menurepository.findByWeekNum(this.getCurrentWeekNumber()).stream().map(MenuMapper.INSTANCE::toDto).toList();
    }

    //Method to check if a menu exists
    @Override
    public boolean checkIfMenuExists(String day) {
        return menurepository.checkIfMenuExists(this.getCurrentWeekNumber(), day);
    }

    //Method to get the id of a menu from its day and week number
    @Override
    public Long getIdFromDayAndWeekNum(String day) {
        return menurepository.getIdFromDayAndWeekNum(this.getCurrentWeekNumber(), day);
    }
    
}
