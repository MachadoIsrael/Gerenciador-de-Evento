package com.casamento.wedding_api.controller;

import com.casamento.wedding_api.dto.Casamento.AtualizarCasamentoDTO;
import com.casamento.wedding_api.dto.Casamento.CasamentoResponseDTO;
import com.casamento.wedding_api.dto.Casamento.CriarCasamentoDTO;
import com.casamento.wedding_api.service.CasamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/casamentos")
@RequiredArgsConstructor
public class CasamentoController {

    private final CasamentoService casamentoService;


    @GetMapping("/{id}")
    public ResponseEntity<CasamentoResponseDTO> findById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(casamentoService.findById(id));

    }


    @PutMapping("/{id}")
    public ResponseEntity<CasamentoResponseDTO> update(
            @PathVariable Long id,
            @RequestBody AtualizarCasamentoDTO atualizarCasamentoDTO
    ) {

        return ResponseEntity.ok(casamentoService.atualizar(id, atualizarCasamentoDTO));

    }

    @PostMapping
    public ResponseEntity<CasamentoResponseDTO> criar(
            @RequestBody CriarCasamentoDTO dto) {

        CasamentoResponseDTO casamento = casamentoService.criar(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(casamento);
    }
}
