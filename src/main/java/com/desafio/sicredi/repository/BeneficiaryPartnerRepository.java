package com.desafio.sicredi.repository;

import com.desafio.sicredi.entities.BeneficiaryPartnerLegalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiaryPartnerRepository extends JpaRepository<BeneficiaryPartnerLegalEntity, String> {
}
