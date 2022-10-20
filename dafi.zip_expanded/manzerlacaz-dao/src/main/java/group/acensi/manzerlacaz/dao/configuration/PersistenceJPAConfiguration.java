/*
 * Copyright (C) Acensi Group
 * 
 * All Rights Reserved Unauthorized copying of this file,
 * via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 */

package group.acensi.manzerlacaz.dao.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import liquibase.integration.spring.SpringLiquibase;

/**
 *
 *
 * @author Nadeem Nayeck <nadeem.nayeck@acensi.group>
 */
@Configuration
@EnableJpaRepositories(basePackages = "group.acensi.dafi.dao")
public class PersistenceJPAConfiguration {
	
	@Bean("springLiquibase")
	public SpringLiquibase liquibaseUpdate(DataSource dataSource) {
	    SpringLiquibase liquibase = new SpringLiquibase();
	    liquibase.setChangeLog("classpath:/database/changelog/changelog-master.xml");
	    liquibase.setDataSource(dataSource);
	    return liquibase;
	}
	
	@Bean
	@DependsOn("springLiquibase")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);
		return em;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}


}
