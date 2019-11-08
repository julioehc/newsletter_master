package com.newsletter.service.subscriptionservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.newsletter.service.subscriptionservice.model.Subscription;

@Service
public class EventService {

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;
    private String eventServiceUrl;

    public EventService(String eventServiceUrl) {
        this.eventServiceUrl = eventServiceUrl;
    }

    //Method to call to email-service
    public String sendEvent(Subscription subscription) {
        ResponseEntity<?> response = restTemplate.postForEntity(eventServiceUrl + "/sendEvent",subscription,String.class);
        return response.getBody().toString();
    }
}
