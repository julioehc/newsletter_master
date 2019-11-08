package com.newsletter.service.subscriptionservice.services;

import com.newsletter.service.subscriptionservice.model.Subscription;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Data
@Service
public class EmailService {

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;
    private String emailServiceUrl;

    public EmailService(String emailServiceUrl) {
        this.emailServiceUrl = emailServiceUrl;
    }

    public String sendMail (Subscription subscription) {
        ResponseEntity responseEntity = restTemplate.postForEntity(emailServiceUrl + "/sendEmail", subscription,
                String.class);
        return responseEntity.getBody().toString();
    }


}
