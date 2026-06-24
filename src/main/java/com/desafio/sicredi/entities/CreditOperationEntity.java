package com.desafio.sicredi.entities;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "credit_operation")
public class CreditOperationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idOperacaoCredito;
    private Integer idAssociado;
    private BigDecimal valorOperacao;
    private String segmento;
    private String codigoProdutoCredito;
    private String codigoConta;
    private Double areaBeneficiadaHa;
    private Instant dataContratacao;
}