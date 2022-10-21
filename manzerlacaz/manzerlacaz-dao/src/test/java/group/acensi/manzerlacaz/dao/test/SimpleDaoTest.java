/*
 * Copyright (C) Acensi Group
 * 
 * All Rights Reserved Unauthorized copying of this file,
 * via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 */

package group.acensi.manzerlacaz.dao.test;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import group.acensi.manzerlacaz.dao.UtilisateurRepository;
import group.acensi.manzerlacaz.dao.configuration.PersistenceJPAConfiguration;
import group.acensi.manzerlacaz.entities.Utilisateur;

/**
 * Unit test for upgrade of db on startup
 *
 * @author Nadeem Nayeck <nadeem.nayeck@acensi.group>
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { PersistenceJPAConfiguration.class,
		group.acensi.manzerlacaz.test.EmbeddedDataSourceConfiguration.class, TransactionConfiguration.class}, loader = AnnotationConfigContextLoader.class)
class SimpleDaoTest {

	@Autowired
	private UtilisateurRepository repository;

	@Test
	void testCreateUtilisateur() throws Exception {
		Utilisateur u = new Utilisateur();
		u.setPassword("password");
		u.setUsername("username");
		u = this.repository.save(u);
		Assert.assertNotNull(u.getId());
		Assert.assertNotNull(u.getCreated());
		Assert.assertNotNull(u.getLastModified());
	}

}
