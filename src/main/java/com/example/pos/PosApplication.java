package com.example.pos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

// import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
 
public class PosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PosApplication.class, args);
	}

}
