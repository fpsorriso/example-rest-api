package com.texo.worstmovie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class WorstMovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorstMovieApplication.class, args);
	}

}
