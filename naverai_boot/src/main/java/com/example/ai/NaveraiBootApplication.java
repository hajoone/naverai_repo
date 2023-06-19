package com.example.ai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import chatbot.PizzaMapper;

@SpringBootApplication
@ComponentScan
@ComponentScan(basePackages = {"cfr","pose","stt_csr","tts_voice","mymapping","ocr","chatbot"})
@ComponentScan(basePackages = "objectdetection")

@MapperScan(basePackageClasses = PizzaMapper.class)
public class NaveraiBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(NaveraiBootApplication.class, args);
	}

}
