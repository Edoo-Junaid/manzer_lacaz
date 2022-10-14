package group.acensi.dafi.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
	
	@GetMapping("/getMenu")
	public Menu getMenu() {
		
		return null;		
	}
	
	@PostMapping("/addMenu")
	public Menu 
	
}
