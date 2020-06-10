package com.example.library;

import com.example.library.database.src.team.library.demo.Book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		Book.GetTypeRank();
	}


}
