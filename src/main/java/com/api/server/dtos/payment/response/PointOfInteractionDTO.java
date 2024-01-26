package com.api.server.dtos.payment.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PointOfInteractionDTO {
    @JsonProperty("transaction_data")
    private TransactionDataDTO transactionData;
}
