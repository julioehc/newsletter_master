package com.newsletter.service.eventservice.rest;

import com.newsletter.service.eventservice.model.Subscription;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventServiceController {

    @PostMapping("/sendEvent")
    @ResponseBody
    public String createEvent(@RequestBody Subscription subscription){return "success";}
}
