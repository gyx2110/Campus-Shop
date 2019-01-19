package com.cumt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class CumtApplication {

	public static void main(String[] args) {
		SpringApplication.run(CumtApplication.class, args);
	}

}

