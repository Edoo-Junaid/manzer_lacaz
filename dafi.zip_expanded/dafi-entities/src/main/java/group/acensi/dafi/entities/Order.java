package group.acensi.dafi.entities;

import group.acensi.dafi.entities.base.AbstractEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Order extends AbstractEntity {

	private long payment;
	private String option;

}
