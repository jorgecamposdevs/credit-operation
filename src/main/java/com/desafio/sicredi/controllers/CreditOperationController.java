package com.desafio.sicredi.controllers;

import com.desafio.sicredi.dtos.requests.CreditOperationRequestDTO;
import com.desafio.sicredi.dtos.responses.CreditOperationDetailResponseDTO;
import com.desafio.sicredi.dtos.responses.CreditOperationResponseDTO;
import com.desafio.sicredi.facades.CreditOperationFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/v1/credits")
@RequiredArgsConstructor
public class CreditOperationController {

    private final CreditOperationFacade creditOperationFacade;

    @Operation(
            summary = "Contratar crédito",
            description = "Realiza a contratação de uma nova operação de crédito"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Crédito contratado com sucesso",
                    content = @Content(schema = @Schema(implementation = CreditOperationResponseDTO.class))),

            @ApiResponse(responseCode = "400", description = "Requisição inválida"),

            @ApiResponse(responseCode = "422", description = "Crédito não permitido para os parâmetros informados"),

            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity<CreditOperationResponseDTO> contractCredit(@RequestBody @Valid CreditOperationRequestDTO creditOperationRequestDTO) {
        return new ResponseEntity<>(creditOperationFacade.contractCredit(creditOperationRequestDTO), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Consultar operação de crédito",
            description = "Consulta os detalhes de uma operação de crédito pelo identificador"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação encontrada",
                    content = @Content(schema = @Schema(implementation = CreditOperationDetailResponseDTO.class))),

            @ApiResponse(responseCode = "404", description = "Operação não encontrada"),

            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{idOperacaoCredito}")
    public ResponseEntity<CreditOperationDetailResponseDTO> getDetailCreditOperationById(@PathVariable String idOperacaoCredito) {
        return new ResponseEntity<>(creditOperationFacade.getDetailCreditOperationById(idOperacaoCredito), HttpStatus.OK);
    }
}