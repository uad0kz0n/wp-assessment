package com.worldpay.offers;

import javax.sql.DataSource;

import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@ComponentScan
@EntityScan("com.worldpay.offers")
@EnableJpaRepositories("com.worldpay.offers")
@EnableAutoConfiguration
//@EnableEurekaClient
@PropertySource("classpath:db-config.properties")
public class SimpleOfferApplication {

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "offer-application");
		SpringApplication.run(SimpleOfferApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean h2servletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
		registration.addUrlMappings("/console/*");
		return registration;
	}

	@Bean
	public DataSource dataSource() {
		DataSource dataSource = (new EmbeddedDatabaseBuilder()).setType(EmbeddedDatabaseType.H2).addScript("classpath:testdb/schema.sql")
				.addScript("classpath:testdb/data.sql").build();
		return dataSource;
	}
}
