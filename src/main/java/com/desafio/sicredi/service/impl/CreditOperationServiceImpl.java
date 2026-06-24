package com.desafio.sicredi.service.impl;

import com.desafio.sicredi.client.ProductCreditClient;
import com.desafio.sicredi.entities.CreditOperationEntity;
import com.desafio.sicredi.repository.CreditOperationRepository;
import com.desafio.sicredi.service.CreditOperationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreditOperationServiceImpl implements CreditOperationService {

    @Autowired
    private CreditOperationRepository creditOperationRepository;

    @Autowired
    private ProductCreditClient productCreditClient;

    @Override
    public CreditOperationEntity contractCredit(CreditOperationEntity creditOperationEntity) {
        log.info("Credit operation successfully created {}", creditOperationEntity);


        Boolean allowedToHire = productCreditClient.isCreditEligible(creditOperationEntity.getCodigoProdutoCredito(),
                creditOperationEntity.getSegmento(), String.valueOf(creditOperationEntity.getValorOperacao()));

        if (!Boolean.TRUE.equals(allowedToHire)) {
            throw new RuntimeException("Credit operation not allowed");
        }

        if ("AGRO".equalsIgnoreCase(creditOperationEntity.getSegmento())) {

            if (creditOperationEntity.getAreaBeneficiadaHa() == null || creditOperationEntity.getAreaBeneficiadaHa() <= 0) {
                throw new RuntimeException("Credit operation for AGRO segment requires areaBeneficiadaHa > 0");
            }
        }

        log.info("Credit operation approved: {}", creditOperationEntity);

        return creditOperationRepository.save(creditOperationEntity);
    }

    @Override
    public CreditOperationEntity getDetailCreditOperationById(String idOperacaoCredito) {
        return creditOperationRepository.getReferenceById(idOperacaoCredito);
    }
}