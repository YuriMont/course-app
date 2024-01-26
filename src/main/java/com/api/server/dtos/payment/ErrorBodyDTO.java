package com.api.server.dtos.payment;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ErrorBodyDTO {
    private String message;
    private String error;
    private String status;
}
