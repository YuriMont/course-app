package com.api.server.dtos.payment.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaymentResponseDTO {
    private String id;
    @JsonProperty("date_of_expiration")
    private Date dateOfExpiration;
    private String status;
    private String description;
    @JsonProperty("transaction_amount")
    private Double transactionAmount;
    @JsonProperty("external_reference")
    private String externalReference;
    @JsonProperty("notification_url")
    private String notificationUrl;
    @JsonProperty("point_of_interaction")
    private PointOfInteractionDTO pointOfInteraction;
}
