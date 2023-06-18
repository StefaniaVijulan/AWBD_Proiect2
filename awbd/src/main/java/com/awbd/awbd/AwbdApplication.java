package com.awbd.awbd;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@EnableFeignClients
@EnableDiscoveryClient
@RequestMapping("/awbd")
public class AwbdApplication {
	@Value("${server.port}")
	private String port;

	@GetMapping("/portProduct")
	public String portNow(){
		return "application is on port: " + port;
	}

	public static void main(String[] args) {
		SpringApplication.run(AwbdApplication.class, args);
	}

}
