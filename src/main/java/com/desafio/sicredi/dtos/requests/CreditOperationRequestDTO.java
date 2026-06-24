package com.desafio.sicredi.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @NotNull(message = "O id do associado é obrigatório")
    private Integer idAssociado;

    @NotNull(message = "O valor da operação é obrigatório")
    @Positive(message = "O valor da operação deve ser maior que zero")
    private BigDecimal valorOperacao;

    @NotBlank(message = "O segmento é obrigatório")
    private String segmento;

    @NotBlank(message = "O código do produto de crédito é obrigatório")
    private String codigoProdutoCredito;

    @NotBlank(message = "O código da conta é obrigatório")
    private String codigoConta;
    private Double areaBeneficiadaHa;
}