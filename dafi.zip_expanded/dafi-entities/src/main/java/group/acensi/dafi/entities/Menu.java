package group.acensi.dafi.entities;



import java.util.List;

import group.acensi.dafi.entities.base.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Menu extends AbstractEntity {
	@ManyToMany
	@JoinTable(name = "order", joinColumns = { @JoinColumn(name = "menu_id") }, inverseJoinColumns = {
			@JoinColumn(name = "user_id") })
	private List<Utilisateur> users;
	private String description;
	private String price;
	private String date;

	

}
