package com.api.server.infra.payment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class MercadoPagoConfig {

    @Value("${api.payment.mercadopago.url}")
    private String urlMercadoPago;

    @Value("${api.payment.mercadopago.token}")
    private String tokenAccess;

    @Bean
    public WebClient webClient(){
       return WebClient.builder()
               .baseUrl(urlMercadoPago)
               .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer "+tokenAccess)
               .build();
    }
}


