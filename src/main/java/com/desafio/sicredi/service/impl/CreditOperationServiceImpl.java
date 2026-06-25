package com.desafio.sicredi.service.impl;

import com.desafio.sicredi.client.ProductCreditClient;
import com.desafio.sicredi.dtos.responses.ProductCreditResponseDTO;
import com.desafio.sicredi.entities.BeneficiaryPartnerLegalEntity;
import com.desafio.sicredi.entities.CreditOperationEntity;
import com.desafio.sicredi.exceptions.handlers.CreditOperationException;
import com.desafio.sicredi.repository.BeneficiaryPartnerRepository;
import com.desafio.sicredi.repository.CreditOperationRepository;
import com.desafio.sicredi.service.CreditOperationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreditOperationServiceImpl implements CreditOperationService {

    private final CreditOperationRepository creditOperationRepository;

    private final BeneficiaryPartnerRepository beneficiaryPartnerRepository;

    private final ProductCreditClient productCreditClient;

    private static final String SEGMENT_AGRO = "AGRO";
    private static final String SEGMENT_PJ = "PJ";

    @Override
    public CreditOperationEntity contractCredit(CreditOperationEntity creditOperationEntity) {

        ProductCreditResponseDTO productCreditResponseDTO = productCreditClient.isCreditEligible(creditOperationEntity.getCodigoProdutoCredito(),
                creditOperationEntity.getSegmento(), String.valueOf(creditOperationEntity.getValorOperacao()));

        log.info(
                "Credit eligibility checked. ProductCode: {}, Segment: {}, Amount: {}, Allowed: {}",
                creditOperationEntity.getCodigoProdutoCredito(),
                creditOperationEntity.getSegmento(),
                creditOperationEntity.getValorOperacao(),
                productCreditResponseDTO.getPermiteContratar());

        if (productCreditResponseDTO.getPermiteContratar().equals(Boolean.FALSE)) {
            throw new CreditOperationException("error.unprocessable-entity");
        }

        if (SEGMENT_AGRO.equalsIgnoreCase(creditOperationEntity.getSegmento())) {

            if (creditOperationEntity.getAreaBeneficiadaHa() == null || creditOperationEntity.getAreaBeneficiadaHa() <= 0) {
                throw new RuntimeException("Credit operation for AGRO segment requires areaBeneficiadaHa > 0");
            }
        }

        CreditOperationEntity operation =
                creditOperationRepository.save(creditOperationEntity);

        if (SEGMENT_PJ.equalsIgnoreCase(operation.getSegmento())) {

            BeneficiaryPartnerLegalEntity beneficiary =
                    BeneficiaryPartnerLegalEntity.builder()
                            .idOperacaoCredito(operation.getIdOperacaoCredito())
                            .idAssociado(operation.getIdAssociado())
                            .build();

            beneficiaryPartnerRepository.save(beneficiary);

            log.info(
                    "Beneficiary relationship created for operation {}",
                    operation.getIdOperacaoCredito());
        }

        log.info("Credit operation approved: {}", creditOperationEntity);

        return operation;
    }

    @Override
    public CreditOperationEntity getDetailCreditOperationById(String idOperacaoCredito) {
        return creditOperationRepository.getReferenceById(idOperacaoCredito);
    }
}