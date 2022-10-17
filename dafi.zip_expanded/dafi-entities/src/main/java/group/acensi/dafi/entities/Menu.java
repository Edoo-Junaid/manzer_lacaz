package group.acensi.dafi.entities;

import group.acensi.dafi.entities.base.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Menu extends AbstractEntity{
   private String day;
   private String name;
   private String price;
   private String option;
}