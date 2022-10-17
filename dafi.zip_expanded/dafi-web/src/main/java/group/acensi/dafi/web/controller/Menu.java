package group.acensi.dafi.web.controller;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Menu {
	private String day;
	private String name;
	
	private List<String> type;



	public Menu(String day, String name,List<String> type1) {
		this.day=day;
		this.name=name;
		this.type=type1;
	}
}
