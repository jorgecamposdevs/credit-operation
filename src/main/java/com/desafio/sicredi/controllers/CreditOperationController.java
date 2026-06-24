package com.desafio.sicredi.controllers;

import com.desafio.sicredi.dtos.requests.CreditOperationRequestDTO;
import com.desafio.sicredi.dtos.responses.CreditOperationDetailResponseDTO;
import com.desafio.sicredi.dtos.responses.CreditOperationResponseDTO;
import com.desafio.sicredi.facades.CreditOperationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/credits")
@RequiredArgsConstructor
public class CreditOperationController {

    @Autowired
    private CreditOperationFacade creditOperationFacade;

    @PostMapping
    public ResponseEntity<CreditOperationResponseDTO> contractCredit(@RequestBody CreditOperationRequestDTO creditOperationRequestDTO) {
        return new ResponseEntity<>(creditOperationFacade.contractCredit(creditOperationRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{idOperacaoCredito}")
    public ResponseEntity<CreditOperationDetailResponseDTO> getDetailCreditOperationById(@PathVariable String idOperacaoCredito) {
        return new ResponseEntity<>(creditOperationFacade.getDetailCreditOperationById(idOperacaoCredito), HttpStatus.OK);
    }
}