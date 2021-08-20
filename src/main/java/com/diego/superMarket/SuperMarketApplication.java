package com.diego.superMarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication//(exclude = {SecurityAutoConfiguration.class})
public class SuperMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperMarketApplication.class, args);
	}

}
