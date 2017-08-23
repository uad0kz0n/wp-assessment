package com.worldpay.offers;

import javax.sql.DataSource;

import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@SpringBootApplication
@PropertySource("classpath:db-config.properties")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
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
