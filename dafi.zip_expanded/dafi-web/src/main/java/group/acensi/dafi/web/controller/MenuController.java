package group.acensi.dafi.web.controller;

import group.acensi.dafi.entities.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import group.acensi.dafi.service.api.MenuService;
import group.acensi.dafi.service.dto.MenuDto;


@RestController
@RequestMapping("/api/auth/menu")
public class MenuController {
	
    @Autowired
    private MenuService menuService;
    
    @PostMapping("/addMenu")
    public  ResponseEntity<Menu> addMenu(@RequestBody Menu menu) {
       MenuDto menuDto = new MenuDto();
       menuDto.setName(menu.getName());
       menuDto.setDay(menu.getDay());
       menuDto.setPrice(menu.getPrice());
       menuDto.setOption(menu.getPrice());

       return new ResponseEntity<Menu>(menuService.createMenu(menuDto), HttpStatus.CREATED);
    }
    
	@GetMapping("/getAllMenu")
	public List<Menu> getMenu() {
        return menuService.listAllMenu();
		
	}
//

//	@GetMapping("/getAllMenu")
//	public List<Menu> getAllMenu() {
//		String type[]={"Veg","Non-Veg"};
//		List<String> type1=Arrays.asList(type);
//		return Arrays.asList(new Menu("Monday","Briani",type1),new Menu("Tuesday","Briani",type1),new Menu("Wednesday","Briani",type1),new Menu("Thursday","Brinai",type1),new Menu("Friday","Briani",type1));
//	}

	
//	@PostMapping("/addMenu")
//	public void addMenu() {
//		
//	}
//	
//	@GetMapping("/triaal")
//	public String trial() {
//		return "hello";
//	}
	
	
	
}
