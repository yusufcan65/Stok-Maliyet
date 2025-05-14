package com.inonu.stok_takip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StokTakipApplication {

	public static void main(String[] args) {
		SpringApplication.run(StokTakipApplication.class, args);
	}

}
