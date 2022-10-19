package group.acensi.dafi.web.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import group.acensi.dafi.service.api.MenuService;
import group.acensi.dafi.service.dto.MenuDto;
import group.acensi.dafi.service.mapper.MenuMapper;
import group.acensi.dafi.web.payload.CreateMenuRequest;


@RestController
@RequestMapping("/api/auth/menu")
public class MenuController {
	
    @Autowired
    private MenuService menuService;
    
    @PostMapping("/addMenu")
    public List<MenuDto> addMenu(@RequestBody List<CreateMenuRequest> menu){
        List<MenuDto> returnMenuList = new ArrayList<>();
        for(CreateMenuRequest menuInList: menu) {
            MenuDto menuDto= new MenuDto();
            menuDto.setDay(menuInList.day());
            menuDto.setOption(menuInList.option());
            menuDto.setPrice(menuInList.price());
            menuDto.setDescription(menuInList.description());
            menuDto.setWeekNum(menuService.getCurrentWeekNumber());
            returnMenuList.add(MenuMapper.INSTANCE.toDto(menuService.createMenu(menuDto)));
        }
        return returnMenuList;
    }
    
	@GetMapping("/getAllMenu")
	public List<MenuDto> getMenu() {
        return menuService.listAllMenu();
	}

    @GetMapping("/getCurrentMenu")
    public List<MenuDto> getCurrentMenu() {
        return menuService.listCurrentMenu();
    }
	
}
