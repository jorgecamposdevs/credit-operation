package com.desafio.sicredi.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditOperationDetailResponseDTO {

    private String idOperacaoCredito;
    private Integer idAssociado;
    private BigDecimal valorOperacao;
    private String segmento;
    private String codigoProdutoCredito;
    private String codigoConta;
    private Double areaBeneficiadaHa;
    private Instant dataContratacao;
}
