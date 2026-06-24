package com.desafio.sicredi.repository;

import com.desafio.sicredi.entities.CreditOperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditOperationRepository extends JpaRepository<CreditOperationEntity, String> {
}
