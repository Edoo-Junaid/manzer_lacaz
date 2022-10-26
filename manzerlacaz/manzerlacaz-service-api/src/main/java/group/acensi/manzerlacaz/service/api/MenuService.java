package group.acensi.manzerlacaz.service.api;

import java.util.List;

import group.acensi.manzerlacaz.entities.Menu;
import group.acensi.manzerlacaz.service.dto.MenuDto;




public interface MenuService {
    public Menu createMenu(MenuDto menuDto);
    public List<MenuDto> listAllMenu();
    public int getCurrentWeekNumber();
    public List<MenuDto> listCurrentMenu();
    public boolean checkIfMenuExists(String day);
    public Long getIdFromDayAndWeekNum(String day);
}
