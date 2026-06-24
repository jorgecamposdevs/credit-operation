package com.desafio.sicredi.service;

import com.desafio.sicredi.entities.CreditOperationEntity;

public interface CreditOperationService {

    CreditOperationEntity contractCredit(CreditOperationEntity creditOperationEntity);
    CreditOperationEntity getDetailCreditOperationById(String idOperacaoCredito);
}