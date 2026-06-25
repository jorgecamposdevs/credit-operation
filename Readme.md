# Credit Operation API

## Sobre o Projeto

O crédito representa uma parte importante do negócio do Sicredi. Com o objetivo de manter as soluções atualizadas e relevantes para os associados, este projeto implementa um backend responsável pela contratação de operações de crédito.

O processo de aquisição de crédito inicia-se quando o associado manifesta interesse em obter crédito junto à cooperativa. Após a simulação e escolha do produto mais adequado, a operação é contratada, gerando um título de crédito persistido na base de dados.

Esta aplicação implementa a primeira etapa do processo de contratação, disponibilizando uma API REST desenvolvida em Java com Spring Boot.

---

## Tecnologias Utilizadas

* Java 17
* Spring Boot 4
* Spring Data JPA
* PostgreSQL
* Docker
* Docker Compose
* WebClient
* Lombok
* Gradle
* Springdoc OpenAPI (Swagger)

---

## Arquitetura

O projeto foi desenvolvido seguindo uma arquitetura em camadas:

* **Controller**: exposição dos endpoints REST.
* **Service**: implementação das regras de negócio.
* **Repository**: acesso aos dados.
* **Entity**: representação das entidades persistidas.
* **Client**: comunicação com serviços externos.

---

## Funcionalidades

### Contratação de Crédito

A API permite registrar uma nova operação de crédito através de um endpoint REST.

### Validações Implementadas

* Validação de elegibilidade do produto de crédito através de um serviço externo.
* Operações do segmento **AGRO** somente podem ser contratadas quando:

    * `areaBeneficiadaHa` estiver preenchido;
    * `areaBeneficiadaHa` for maior que zero.
* Operações do segmento **PJ** geram automaticamente um registro adicional contendo o vínculo entre a operação e o associado beneficiário.

---

## Endpoint Principal

### Contratar Operação de Crédito

**POST**

```http
POST /credit-operations
```

### Exemplo de Requisição

```json
{
  "idOperacaoCredito": "OP12345",
  "idAssociado": 1001,
  "valorOperacao": 3000.00,
  "segmento": "AGRO",
  "codigoProdutoCredito": "903C",
  "codigoConta": "123456-7",
  "areaBeneficiadaHa": 10.5
}
```

### Exemplo de Resposta

```json
{
  "idOperacaoCredito": "OP12345",
  "idAssociado": 1001,
  "valorOperacao": 3000.00,
  "segmento": "AGRO",
  "codigoProdutoCredito": "903C",
  "codigoConta": "123456-7",
  "areaBeneficiadaHa": 10.5,
  "dataContratacao": "2026-06-24T10:00:00"
}
```

---

## Serviço Externo

Para validar se um produto de crédito permite contratação, a aplicação consome o seguinte serviço:

```http
GET https://desafio-credito-sicredi.wiremockapi.cloud/produtos-credito/{codigoProduto}/permite-contratacao
```

Parâmetros:

| Parâmetro       | Descrição             |
| --------------- | --------------------- |
| segmento        | Segmento do associado |
| valorFinanciado | Valor solicitado      |

Exemplo:

```http
GET /produtos-credito/903C/permite-contratacao?segmento=AGRO&valorFinanciado=3000
```

Resposta esperada:

```json
{
  "permiteContratar": true
}
```

---

## Executando a Aplicação

### Pré-requisitos

* Docker
* Docker Compose

### Executar com Docker

```bash
docker compose up --build
```

A aplicação estará disponível em:

```http
http://localhost:8080
```

### Executar com Swagger

```http
http://localhost:8080/swagger-ui/index.html#/
```

---

## Banco de Dados

O projeto utiliza PostgreSQL como banco de dados.

As tabelas principais são:

* `credit_operation`
* `beneficiary_partner`

---

## Melhorias Futuras

* Implementação de testes unitários.
* Implementação de testes de integração.
* Tratamento global de exceções.
* Documentação da API com OpenAPI/Swagger.
* Pipeline CI/CD.

---

## Autor

**Jorge Campos de Jesus**
