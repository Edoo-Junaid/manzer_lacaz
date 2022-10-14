/*
 * Copyright (C) Acensi Group
 * 
 * All Rights Reserved Unauthorized copying of this file,
 * via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 */

package group.acensi.dafi.entities.base;

import java.io.Serializable;
import java.time.ZonedDateTime;

import group.acensi.dafi.entities.listener.EntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

/**
 * Base class for any entity, implemeting standard primary key
 *
 * @author Nadeem Nayeck <nadeem.nayeck@acensi.group>
 */
@MappedSuperclass
@EntityListeners({ EntityListener.class })
@Data
public abstract class AbstractEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private ZonedDateTime lastModified;

	@Temporal(TemporalType.TIMESTAMP)
	private ZonedDateTime created;


}
