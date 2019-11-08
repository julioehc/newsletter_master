package com.newsletter.subscription.emailservice.rest;

import com.newsletter.subscription.emailservice.model.Subscription;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailServiceController {

    @PostMapping("/sendEmail")
    @ResponseBody
    public String sendEmail(@RequestBody Subscription subscription) {return "success";}

}
