package com.ssafy.travelog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class TravelogApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelogApplication.class, args);
	}

}
