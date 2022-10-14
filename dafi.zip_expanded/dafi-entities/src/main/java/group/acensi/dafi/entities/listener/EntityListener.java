/*
 * Copyright (C) Acensi Group
 * 
 * All Rights Reserved Unauthorized copying of this file,
 * via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 */

package group.acensi.dafi.entities.listener;

import java.time.ZonedDateTime;

import group.acensi.dafi.entities.base.AbstractEntity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

/**
 * Automatically sets created and lastmodified fields on entities
 *
 * @author Nadeem Nayeck <nadeem.nayeck@acensi.group>
 */
public final class EntityListener {
	
	@PrePersist
	public void onPrePersist(AbstractEntity abstractEntity) {
		abstractEntity.setCreated(ZonedDateTime.now());
		abstractEntity.setLastModified(abstractEntity.getCreated());
	}

	@PreUpdate
	public void onPreUpdate(AbstractEntity abstractEntity) {
		abstractEntity.setLastModified(ZonedDateTime.now());
	}
}
