package com.casamento.wedding_api.repository;

import com.casamento.wedding_api.entity.GrupoConvidados;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrupoConvidadoRepository extends JpaRepository<GrupoConvidados, Long> {

    List<GrupoConvidados> findByCasamentoId(Long casamentoId);
}
