package com.api.server.dtos.payment.request;

import com.api.server.dtos.payment.request.PayerDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentRequestDTO {
    private String description;
    @JsonProperty("external_reference")
    private String externalReference;
    private PayerDTO payer;
    @JsonProperty("payment_method_id")
    private String paymentMethodId;
    @JsonProperty("date_of_expiration")
    private String dateOfExpiration;
    @JsonProperty("transaction_amount")
    private Double transactionAmount;
}



