package com.desafio.sicredi.client;

import com.desafio.sicredi.dtos.responses.ProductCreditResponseDTO;
import com.desafio.sicredi.exceptions.handlers.CreditOperationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Component
public class ProductCreditClient {

    @Autowired
    private WebClient webClient;

    private static final String BASE_URL =
            "https://desafio-credito-sicredi.wiremockapi.cloud";

    public ProductCreditResponseDTO isCreditEligible(String code, String segment, String value) {

        try {

            return webClient.get()
                    .uri(BASE_URL + "/produtos-credito/{codigo}/permite-contratacao?segmento={segmento}&valorFinanciado={valor}",
                            code, segment, value)
                    .retrieve()
                    .bodyToMono(ProductCreditResponseDTO.class)
                    .block();
        } catch (Exception ex) {

            throw new CreditOperationException(
                    "Error checking credit eligibility",
                    ex
            );
        }
    }
}