package com.example.loadbalance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@RibbonClient(name = "awbd-load", configuration = RibbonConfiguration.class)
public class LoadbalanceApplication {


	@GetMapping("/invoke")
	public String invoke() {
		return template().getForObject("http://awbd-load/awbd/portProduct" , String.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(LoadbalanceApplication.class, args);
	}


	@Bean
	@LoadBalanced
	public RestTemplate template() {
		return new RestTemplate();
	}
}
