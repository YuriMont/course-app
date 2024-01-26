package com.api.server.dtos.payment.request;

import com.api.server.dtos.payment.request.IdentificationDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PayerDTO {
    private String email;
    private IdentificationDTO identification;
}
