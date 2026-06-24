package com.desafio.sicredi.facades;

import com.desafio.sicredi.dtos.requests.CreditOperationRequestDTO;
import com.desafio.sicredi.dtos.responses.CreditOperationDetailResponseDTO;
import com.desafio.sicredi.dtos.responses.CreditOperationResponseDTO;

public interface CreditOperationFacade {

    CreditOperationResponseDTO contractCredit(CreditOperationRequestDTO creditOperationRequestDTO);

    CreditOperationDetailResponseDTO getDetailCreditOperationById(String idOperacaoCredito);
}