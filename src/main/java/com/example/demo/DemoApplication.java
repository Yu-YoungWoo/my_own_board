package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// @Bean
    //  public InternalResourceViewResolver setupViewResolver() {
     
    //         InternalResourceViewResolver resolver = new InternalResourceViewResolver();
     
    //         resolver.setPrefix("/WEB-INF/views/");
    //         resolver.setSuffix(".jsp");
    //         return resolver;
    //  }
}
