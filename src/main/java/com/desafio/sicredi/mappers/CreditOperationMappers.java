package com.desafio.sicredi.mappers;

import com.desafio.sicredi.dtos.requests.CreditOperationRequestDTO;
import com.desafio.sicredi.dtos.responses.CreditOperationDetailResponseDTO;
import com.desafio.sicredi.dtos.responses.CreditOperationResponseDTO;
import com.desafio.sicredi.entities.CreditOperationEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class CreditOperationMappers {

    private final ModelMapper modelMapper;

    public CreditOperationEntity convertCreditOperationRequestDTOToCreditOperationEntity
            (CreditOperationRequestDTO creditOperationRequestDTO) {

        CreditOperationEntity creditOperationEntity = new CreditOperationEntity();

        creditOperationEntity.setIdAssociado(creditOperationRequestDTO.getIdAssociado());
        creditOperationEntity.setValorOperacao(creditOperationRequestDTO.getValorOperacao());
        creditOperationEntity.setSegmento(creditOperationRequestDTO.getSegmento());
        creditOperationEntity.setCodigoProdutoCredito(creditOperationRequestDTO.getCodigoProdutoCredito());
        creditOperationEntity.setCodigoConta(creditOperationRequestDTO.getCodigoConta());
        creditOperationEntity.setAreaBeneficiadaHa(creditOperationRequestDTO.getAreaBeneficiadaHa());

        creditOperationEntity.setDataContratacao(Instant.now());

        return creditOperationEntity;
    }

    public CreditOperationResponseDTO convertCreditOperationEntityToCreditOperationResponseDTO
            (CreditOperationEntity creditOperationEntity) {

        CreditOperationResponseDTO creditOperationResponseDTO = new CreditOperationResponseDTO();

        creditOperationResponseDTO.setIdOperacaoCredito(creditOperationEntity.getIdOperacaoCredito());

        return creditOperationResponseDTO;
    }

    public CreditOperationDetailResponseDTO convertCreditOperationEntityToCreditOperationDetailResponseDTO(
            CreditOperationEntity creditOperationEntity) {
        return modelMapper.map(creditOperationEntity, CreditOperationDetailResponseDTO.class);
    }
}