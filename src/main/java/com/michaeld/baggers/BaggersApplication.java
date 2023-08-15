package com.michaeld.baggers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.michaeld.baggers.services.FileService;

import jakarta.annotation.Resource;

@SpringBootApplication
public class BaggersApplication implements CommandLineRunner {

	@Resource
	private FileService FileService;
	
	public static void main(String[] args) {
		SpringApplication.run(BaggersApplication.class, args);
	}
	
	public void run(String... arg) throws Exception {
		this.FileService.init();
	}	
}
