/*
 * Copyright (C) Acensi Group
 * 
 * All Rights Reserved Unauthorized copying of this file,
 * via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 */

package group.acensi.manzerlacaz.dao.test;

import javax.sql.DataSource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import group.acensi.manzerlacaz.dao.UtilisateurRepository;
import group.acensi.manzerlacaz.dao.configuration.PersistenceJPAConfiguration;
import jakarta.persistence.EntityManager;
import liquibase.integration.spring.SpringLiquibase;

/**
 * Unit test for upgrade of db on startup
 *
 * @author Nadeem Nayeck <nadeem.nayeck@acensi.group>
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { PersistenceJPAConfiguration.class,
		group.acensi.manzerlacaz.test.EmbeddedDataSourceConfiguration.class }, loader = AnnotationConfigContextLoader.class)
class LiquibaseTest {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private UtilisateurRepository repository;

	@Autowired
	private SpringLiquibase springLiquibase;

	@Autowired
	private DataSource dataSource;

	@Test
	void testLiquibaseBeanCreated() throws Exception {
		Assertions.assertNotNull(entityManager);
		Assertions.assertNotNull(repository);
		Assertions.assertEquals("springLiquibase", springLiquibase.getBeanName());
		Assertions.assertEquals(springLiquibase.getDataSource(), dataSource,
				"The datasource used by liquibase must be the same as the one created by Spring Application Context");
		Assertions.assertEquals(((EntityManagerFactoryInfo) entityManager.getEntityManagerFactory()).getDataSource(),
				dataSource,
				"The datasource used by JPA must be the same as the one created by Spring Application Context");
	}

}
