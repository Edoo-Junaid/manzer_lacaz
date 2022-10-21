/*
 * Copyright (C) Acensi Group
 * 
 * All Rights Reserved Unauthorized copying of this file,
 * via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 */
package group.acensi.manzerlacaz.web.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Value("${spring.application.name}")
	private String applicationName;

	@Value("${spring.profiles.active}")
	private String activeProfiles;

	@Bean
	public OpenAPI apiInfo(BuildProperties buildProperties) {

		String applicationVersion = buildProperties.getVersion() == null? "" : buildProperties.getVersion();
		String title = applicationName + " (Profiles: " + activeProfiles + ")";

		return new OpenAPI().info(new Info().title(title)
				.version(applicationVersion));
	}

	@Bean
	public GroupedOpenApi httpApi() {
		return GroupedOpenApi.builder().group("http").pathsToMatch("/**").build();
	}

}
