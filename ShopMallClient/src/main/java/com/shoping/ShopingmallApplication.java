package com.shoping;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.shoping.dao")
public class ShopingmallApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopingmallApplication.class, args);
	}

}
