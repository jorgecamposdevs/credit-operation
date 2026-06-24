package com.desafio.sicredi.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditOperationRequestDTO {

    private Integer idAssociado;
    private BigDecimal valorOperacao;
    private String segmento;
    private String codigoProdutoCredito;
    private String codigoConta;
    private Double areaBeneficiadaHa;
}