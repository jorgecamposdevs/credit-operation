package com.desafio.sicredi.client;

import com.desafio.sicredi.dtos.responses.ProductCreditResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ProductCreditClient {

    @Autowired
    private WebClient webClient;

    private static final String BASE_URL =
            "https://desafio-credito-sicredi.wiremockapi.cloud";

    public Boolean isCreditEligible(String code, String segment, String value) {

        ProductCreditResponseDTO productCreditResponseDTO =
                webClient.get()
                        .uri(BASE_URL + "/produtos-credito/{codigo}/permite-contratacao?segmento={segmento}&valorFinanciado={valor}",
                                code, segment, value)
                        .retrieve()
                        .bodyToMono(ProductCreditResponseDTO.class)
                        .block();

        return productCreditResponseDTO != null &&
                Boolean.TRUE.equals(productCreditResponseDTO.getPermiteContratar());
    }
}