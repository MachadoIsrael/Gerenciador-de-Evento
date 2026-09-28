package com.casamento.wedding_api.repository;

import com.casamento.wedding_api.entity.Convidado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConvidadoRepository extends JpaRepository<Convidado, Long> {

    List<Convidado> findByGrupoId(Long grupoId);

    void deleteByGrupoId(Long grupoId);
}
