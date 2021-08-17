package com.goanna.demo.moviereview.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class DemoMovieReviewAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoMovieReviewAppApplication.class, args);
	}

}
