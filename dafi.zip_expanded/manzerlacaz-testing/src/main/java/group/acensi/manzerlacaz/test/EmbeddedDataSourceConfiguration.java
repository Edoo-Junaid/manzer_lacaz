/*
 * Copyright (C) Acensi Group
 * 
 * All Rights Reserved Unauthorized copying of this file,
 * via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 */
package group.acensi.manzerlacaz.test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;

/**
 * Simple datasource used for testing
 *
 * @author Nadeem Nayeck <nadeem.nayeck@acensi.group>
 *
 */
@Configuration
public class EmbeddedDataSourceConfiguration {

	private Logger logger = LoggerFactory.getLogger(EmbeddedDataSourceConfiguration.class);
	
	private static final String TEST_CONTAINER_CONFIG = "testcontainers.reuse.enable=true";
	
	@Bean("test-container-config")
	public File testContainerConfig() throws IOException {
		String homeDirPath = System.getProperty("user.home");
		File file = new File(homeDirPath,".testcontainers.properties");
		if (!file.exists() && file.createNewFile()) {
			logger.info("File {} does not exist. Created", file.getAbsolutePath());
		}
		List<String> lines = FileUtils.readLines(file, StandardCharsets.UTF_8);
		if (!lines.contains(TEST_CONTAINER_CONFIG)) {
			FileUtils.write(file, System.lineSeparator() + TEST_CONTAINER_CONFIG, StandardCharsets.UTF_8, true);
		}
		
		return file;
	}

	@Bean
	@DependsOn("docker-postgres")
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		dataSource.setUrl("jdbc:postgresql://localhost:35432/manzerlacaz");
		return dataSource;
	}

	@SuppressWarnings({ "resource", "rawtypes" })
	@Bean("docker-postgres")
	@DependsOn("test-container-config")
	public PostgreSQLContainer dockerPostgres() {
		int containerPort = 5432;
		int localPort = 35432;
		DockerImageName postgres = DockerImageName.parse("postgres:14.5");
		PostgreSQLContainer postgresContainer = new PostgreSQLContainer<>(postgres)
				.withDatabaseName("dafi")
				.withUsername("root")
				.withPassword("root")
				.withExposedPorts(containerPort)
				.withReuse(true)
				.withCreateContainerCmdModifier(cmd -> cmd.withHostConfig(new HostConfig().withPortBindings(
						new PortBinding(Ports.Binding.bindPort(localPort), new ExposedPort(containerPort)))));
		postgresContainer.start();
		return postgresContainer;
	}

}
