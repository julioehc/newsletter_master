package com.newsletter.service.subscriptionservice.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.newsletter.service.subscriptionservice.model.Subscription;
import org.springframework.data.mongodb.repository.Query;


public interface SubscriptionRepo extends MongoRepository <Subscription, Long> {

    @Query("{'email':?0}")
    Subscription findSubscriptionByEmail(String email);
}
