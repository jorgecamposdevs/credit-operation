package com.desafio.sicredi.facades.impl;

import com.desafio.sicredi.dtos.requests.CreditOperationRequestDTO;
import com.desafio.sicredi.dtos.responses.CreditOperationDetailResponseDTO;
import com.desafio.sicredi.dtos.responses.CreditOperationResponseDTO;
import com.desafio.sicredi.facades.CreditOperationFacade;
import com.desafio.sicredi.mappers.CreditOperationMappers;
import com.desafio.sicredi.service.CreditOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreditOperationFacadeImpl implements CreditOperationFacade {

    @Autowired
    private CreditOperationService creditOperationService;

    @Autowired
    private CreditOperationMappers creditOperationMappers;

    @Override
    public CreditOperationResponseDTO contractCredit(CreditOperationRequestDTO creditOperationRequestDTO) {
        return creditOperationMappers.convertCreditOperationEntityToCreditOperationResponseDTO(creditOperationService
                .contractCredit(creditOperationMappers
                        .convertCreditOperationRequestDTOToCreditOperationEntity(
                                creditOperationRequestDTO)));
    }

    @Override
    public CreditOperationDetailResponseDTO getDetailCreditOperationById(String idOperacaoCredito) {
        return creditOperationMappers.convertCreditOperationEntityToCreditOperationDetailResponseDTO(
                creditOperationService.getDetailCreditOperationById(idOperacaoCredito)
        );
    }
}