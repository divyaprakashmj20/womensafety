package com.tus.sosmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
//@EnableAsync
public class SosmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SosmanagementApplication.class, args);
	}

}
