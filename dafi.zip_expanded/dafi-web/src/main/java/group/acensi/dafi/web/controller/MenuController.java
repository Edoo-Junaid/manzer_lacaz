package group.acensi.dafi.web.controller;


import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/menu")
public class MenuController {
	
	@GetMapping("/getMenu")
	public Menu getMenu() {
		String type[]={"Veg","Non-Veg"};
		List<String> type1=Arrays.asList(type);
		return new Menu("Monday","Briani",type1);
	}

	@GetMapping("/getAllMenu")
	public List<Menu> getAllMenu() {
		String type[]={"Veg","Non-Veg"};
		List<String> type1=Arrays.asList(type);
		return Arrays.asList(new Menu("Monday","Briani",type1),new Menu("Tuesday","Briani",type1),new Menu("Wednesday","Briani",type1),new Menu("Thursday","Brinai",type1),new Menu("Friday","Briani",type1));
	}

	
	@PostMapping("/addMenu")
	public void addMenu() {
		
	}
	
	@GetMapping("/triaal")
	public String trial() {
		return "hello";
	}
	
}
