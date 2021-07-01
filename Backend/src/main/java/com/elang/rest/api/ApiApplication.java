package com.elang.rest.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.elang.rest.api.scheduleService.Timer;
import com.elang.rest.api.property.FileStorageProperties;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class ApiApplication {
	
	private final static Timer timer = new Timer();

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
		
//		timer.cekMenangLelang();
	}

}