/*
 * Copyright (C) Acensi Group
 * 
 * All Rights Reserved Unauthorized copying of this file,
 * via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 */

package group.acensi.manzerlacaz.entities.base;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Base class for any entity, implemeting standard primary key
 *
 * @author Nadeem Nayeck <nadeem.nayeck@acensi.group>
 */
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class AbstractKeyValueEntity extends AbstractEntity {

	private String cle;
	
	private String groupe;
	
	private String stringValue;
	
	private Double numberValue;
	
	private LocalDateTime dateTimeValue;
	
	private LocalDate dateValue;
}
