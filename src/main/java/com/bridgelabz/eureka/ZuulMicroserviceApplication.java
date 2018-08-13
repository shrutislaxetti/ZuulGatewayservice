package com.bridgelabz.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.bridgelabz.eureka.filter.SimpleFilter;

@EnableDiscoveryClient
@SpringBootApplication
@EnableZuulProxy
public class ZuulMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulMicroserviceApplication.class, args);
	}
	@Bean
	SimpleFilter simpleFilter() {
		return new SimpleFilter();
	}
	
}
