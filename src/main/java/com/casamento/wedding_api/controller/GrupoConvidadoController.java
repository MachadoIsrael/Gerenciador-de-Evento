package com.casamento.wedding_api.controller;


import com.casamento.wedding_api.dto.GrupoConvidado.CriarGrupoConvidadoDTO;
import com.casamento.wedding_api.dto.GrupoConvidado.GrupoConvidadoResponseDTO;
import com.casamento.wedding_api.service.GrupoConvidadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/casamentos/{casamentoId}/grupos")
@RequiredArgsConstructor
public class GrupoConvidadoController {

    private final GrupoConvidadoService grupoConvidadoService;






    @GetMapping
    public ResponseEntity<List<GrupoConvidadoResponseDTO>> listar(
            @PathVariable Long casamentoId) {

        return ResponseEntity.ok(
                grupoConvidadoService.listarPorCasamento(casamentoId)
        );
    }

    @PostMapping
    public ResponseEntity<GrupoConvidadoResponseDTO> criar(
            @PathVariable Long casamentoId,
            @RequestBody CriarGrupoConvidadoDTO dto) {

        GrupoConvidadoResponseDTO grupo =
                grupoConvidadoService.criar(casamentoId, dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(grupo);
    }


    @PutMapping("/{grupoId}")
    public ResponseEntity<GrupoConvidadoResponseDTO> atualizar(
            @PathVariable Long grupoId,
            @RequestBody CriarGrupoConvidadoDTO dto
    ){
        return ResponseEntity.ok(
                grupoConvidadoService.atualizar(grupoId, dto)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<GrupoConvidadoResponseDTO> remover(@PathVariable Long id){
        grupoConvidadoService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
