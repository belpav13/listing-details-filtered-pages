package com.pavel.listingdetailsfilteredpages;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ListingDetailsFilteredPagesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ListingDetailsFilteredPagesApplication.class, args);
	}

}
