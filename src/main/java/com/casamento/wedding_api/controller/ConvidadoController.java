package com.casamento.wedding_api.controller;


import com.casamento.wedding_api.dto.Convidado.AtualizarConvidadoDTO;
import com.casamento.wedding_api.dto.Convidado.ConfirmarPresencaDTO;
import com.casamento.wedding_api.dto.Convidado.ConvidadoResponseDTO;
import com.casamento.wedding_api.dto.Convidado.CriarConvidadoDTO;
import com.casamento.wedding_api.service.ConvidadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/convidados")
@RequiredArgsConstructor
public class ConvidadoController {

    private final ConvidadoService convidadoService;




    @GetMapping
    public ResponseEntity<List<ConvidadoResponseDTO>> findAll() {
        return ResponseEntity.ok(convidadoService.listaConvidados());
    }


    @GetMapping("/{id}")
    public ResponseEntity<ConvidadoResponseDTO> getConvidado(@PathVariable Long id){
        return ResponseEntity.ok(convidadoService.findById(id));
    }


    @GetMapping("/grupos/{grupoId}")
    public ResponseEntity<List<ConvidadoResponseDTO>> listarPorGrupo(@PathVariable Long grupoId){

        return ResponseEntity.ok(convidadoService.listarPorGrupo(grupoId));
    }


    @PostMapping("/grupos/{grupoId}")
    public ResponseEntity<ConvidadoResponseDTO> criar(
            @PathVariable Long grupoId,
            @RequestBody CriarConvidadoDTO dto
    ){
        ConvidadoResponseDTO convidado = convidadoService.criar(grupoId, dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(convidado);
    }



    @PutMapping("/{id}")
    public ResponseEntity<ConvidadoResponseDTO> atualizar(
            @PathVariable Long grupoId,
            @PathVariable Long convidadoId,
            @RequestBody AtualizarConvidadoDTO dto
    ){
        return ResponseEntity.ok(convidadoService.atualizar(grupoId, convidadoId, dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long grupoId,
            @PathVariable Long id
    ) {

        convidadoService.remover(grupoId, id);

        return ResponseEntity.noContent().build();
    }



    @PatchMapping("/{convidadoId}/presenca")
    public ResponseEntity<ConvidadoResponseDTO> atualizarPresenca(
            @PathVariable Long convidadoId,
            @RequestBody ConfirmarPresencaDTO dto
    ) {
        return ResponseEntity.ok(convidadoService.atualizarStatus(convidadoId, dto));
    }
}
