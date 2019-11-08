package com.newsletter.service.subscriptionservice.rest;

import com.newsletter.service.subscriptionservice.exception.BadRequestException;
import com.newsletter.service.subscriptionservice.dao.SubscriptionRepo;
import com.newsletter.service.subscriptionservice.exception.RequestException;
import com.newsletter.service.subscriptionservice.exception.ServerException;
import com.newsletter.service.subscriptionservice.services.EmailService;
import com.newsletter.service.subscriptionservice.services.EventService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newsletter.service.subscriptionservice.model.Subscription;

import java.util.UUID;

@RestController
public class SubscriptionController {

    private final Logger logger = LoggerFactory.getLogger(SubscriptionController.class);
    @Autowired
    private SubscriptionRepo subscriptionRepo;
    @Autowired
    private EmailService emailService;
    @Autowired
    private EventService eventService;

    public SubscriptionController( EmailService emailService, EventService eventService) {
        this.emailService = emailService;
        this.eventService = eventService;
    }
    /**
     * Method to create a new newsletter subscription
     * @param subscription, information nedeed to subscribe
     * @return
     */
    @PostMapping(value = "/subscribe", produces = {"application/json"})
    @ApiOperation(value = "Subscribre to a newsletter")
    public ResponseEntity<?> subscribeToNewsletter (@RequestBody Subscription subscription) {
        Long uuid = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        Subscription response;
        if (subscription.getEmail() != null) {
          Subscription sub = subscriptionRepo.findSubscriptionByEmail(subscription.getEmail());
          if(sub != null) {
              throw new RequestException("Email already exists");
          }
          subscription.setId(uuid);
          response = subscriptionRepo.save(subscription);
        }
        else {
            throw new BadRequestException("Email is mandatory");
        }
        try {
            String responseEmailService = emailService.sendMail(subscription);
            if(!responseEmailService.equals("success") && !responseEmailService.isEmpty()) {
                throw new ServerException("Error while sending the email");
            }
            logger.info("Email successfully sended ");
            String responseEventService = eventService.sendEvent(subscription);
            if(!responseEventService.equals("success") && !responseEventService.isEmpty()) {
                throw new ServerException("Error while sending the event");
            }
            logger.info("Event successfully sended");
        }
        catch (Exception e) {
            throw new ServerException("Error in server, could not connect to email service");
        }
        return ResponseEntity.ok(response.getId());
    }
}
