package group.acensi.manzerlacaz.web.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import group.acensi.manzerlacaz.service.api.MenuService;
import group.acensi.manzerlacaz.service.dto.MenuDto;
import group.acensi.manzerlacaz.service.mapper.MenuMapper;
import group.acensi.manzerlacaz.web.payload.CreateMenuListRequest;
import group.acensi.manzerlacaz.web.payload.CreateMenuRequest;


@RestController
@RequestMapping("/api/auth/menu")
@CrossOrigin(origins = "*", maxAge = 3600)
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
            menuDto.setWeekNum(menuInList.weekNum());
            returnMenuList.add(MenuMapper.INSTANCE.toDto(menuService.createMenu(menuDto)));
        }
        return returnMenuList;
    }
    
    @PostMapping("/addOneMenu")
    public MenuDto addOneMenu(@RequestBody CreateMenuRequest menu){
    

            MenuDto menuDto= new MenuDto();
            menuDto.setDay(menu.day());
            menuDto.setOption(menu.option());
            menuDto.setPrice(menu.price());
            menuDto.setDescription(menu.description());
           menuDto.setWeekNum(menu.weekNum());
       return  MenuMapper.INSTANCE.toDto(menuService.createMenu(menuDto));
      
      
    }
	@GetMapping("/getAllMenu")
	public List<MenuDto> getMenu() {
        return menuService.listAllMenu();
	}

//    @PostMapping("/getCurrentMenu")
//    public List<MenuDto> getCurrentMenu(@RequestBody CreateMenuListRequest createMenuListRequest) {
//        return menuService.listCurrentMenu(createMenuListRequest.weekNum());
//    }
    

    @GetMapping("/getCurrentMenu")
        public List<MenuDto> getCurrentMenu() {

            return menuService.listCurrentMenu(47);
        }
    
}
