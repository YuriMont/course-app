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
public class TransactionDataDTO {
    @JsonProperty("qr_code")
    private String qrCode;
    @JsonProperty("ticket_url")
    private String ticketUrl;
    @JsonProperty("qr_code_base64")
    private String qrCodeBase64;
}
