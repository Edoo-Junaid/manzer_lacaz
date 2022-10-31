package group.acensi.manzerlacaz.service.api;

import java.util.List;

import group.acensi.manzerlacaz.entities.Menu;
import group.acensi.manzerlacaz.service.dto.MenuDto;




public interface MenuService {

    //Method to create a menu
    public Menu createMenu(MenuDto menuDto);

    //Method to list all menus
    public List<MenuDto> listAllMenu();

    //Method to get the current week number
    public int getCurrentWeekNumber();

    //Method to list the current menu
    public List<MenuDto> listCurrentMenu();

    //Method to check if a menu exists
    public boolean checkIfMenuExists(String day);

    //Method to get the id of a menu from its day and week number
    public Long getIdFromDayAndWeekNum(String day);
}
