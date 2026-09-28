package com.casamento.wedding_api.repository;

import com.casamento.wedding_api.entity.Casamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CasamentoRepository extends JpaRepository<Casamento, Long> {
}
