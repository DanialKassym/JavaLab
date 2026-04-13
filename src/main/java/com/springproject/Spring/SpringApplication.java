package com.springproject.Spring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.springproject.Spring.domain.mapper")
public class SpringApplication {

	public static void main(String[] args) {
		org.springframework.boot.SpringApplication.run(SpringApplication.class, args);
	}

}
