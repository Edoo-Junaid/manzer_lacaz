package group.acensi.dafi.service.api;

import java.util.List;

import group.acensi.dafi.entities.Menu;
import group.acensi.dafi.service.dto.MenuDto;




public interface MenuService {
    public Menu createMenu(MenuDto menuDto);
    public List<MenuDto> listAllMenu();
}
