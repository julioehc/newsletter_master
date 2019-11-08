package com.newsletter.service.subscriptionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;

import com.newsletter.service.subscriptionservice.rest.SubscriptionController;
import com.newsletter.service.subscriptionservice.services.EmailService;
import com.newsletter.service.subscriptionservice.services.EventService;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
        value ={EventService.class, EmailService.class}))
public class SubscriptionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubscriptionServiceApplication.class, args);
    }

    @LoadBalanced
    @Bean
    RestTemplate restTemplate () {
        return new RestTemplate();
    }
    @Bean
    public SubscriptionController subscriptionController() {
        return new SubscriptionController(emailService(),eventService());
    }
    @Bean
    public EmailService emailService() {
        return new EmailService("http://EMAIL-SERVICE");
    }

    @Bean
    public EventService eventService() {
        return new EventService("http://EVENT-SERVICE");
    }


}
