package com.tcs.ins.testcase.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.envers.repository.config.EnableEnversRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.tcs.ins.testcase.support.SpringSecurityAuditorAware;

@Configuration
@EnableJpaAuditing
@EnableEnversRepositories(basePackages = { "com.tcs.ins.testcase.repository" })
public class RepositoryConfig {

	@Bean
	AuditorAware<String> auditorProvider() {
		return new SpringSecurityAuditorAware();
	}
}
