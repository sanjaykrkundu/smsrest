package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.example.demo.mvc.entity.Course;
import com.example.demo.mvc.entity.Student;

@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Course.class);
		config.exposeIdsFor(Student.class);
	}

	@Bean
	EventHandler eventHandler() {
		return new EventHandler();
	}

}