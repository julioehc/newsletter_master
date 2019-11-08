package com.newsletter.service.subscriptionservice.model;

import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "subscription")
@AllArgsConstructor
public class Subscription implements Serializable {
    private static final long serialVersionUID = 2335277150611519595L;

    @ApiModelProperty(required = true)
    private String email;
    private String firstName;
    private Gender gender;
    @ApiModelProperty(required = true)
    private String dateOfBirth;
    @ApiModelProperty(required = true)
    private Boolean consentFlag;
    @ApiModelProperty(required = true)
    private long newsLetterCampaign;
    @ApiModelProperty(hidden = true)
    @Id
    private long id;

}
