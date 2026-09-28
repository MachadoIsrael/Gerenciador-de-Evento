package com.casamento.wedding_api.service;

import com.casamento.wedding_api.dto.GrupoConvidado.CriarGrupoConvidadoDTO;
import com.casamento.wedding_api.dto.GrupoConvidado.GrupoConvidadoResponseDTO;
import com.casamento.wedding_api.entity.Casamento;
import com.casamento.wedding_api.entity.GrupoConvidados;
import com.casamento.wedding_api.repository.CasamentoRepository;
import com.casamento.wedding_api.repository.ConvidadoRepository;
import com.casamento.wedding_api.repository.GrupoConvidadoRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GrupoConvidadoService {

    private final GrupoConvidadoRepository grupoConvidadoRepository;
    private final CasamentoRepository casamentoRepository;
    private final ConvidadoRepository convidadoRepository;


    public List<GrupoConvidadoResponseDTO> findAll() {

        return grupoConvidadoRepository.findAll()
                .stream()
                .map(grupo -> new GrupoConvidadoResponseDTO(
                        grupo.getId(),
                        grupo.getNome(),
                        grupo.getLado()
                ))
                .toList();
    }



    @Transactional
    public void remover(Long id) {
        GrupoConvidados grupoConvidados = grupoConvidadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grupo não encontrado"));

        // Primeiro remove todos os convidados pertencentes ao grupo
        convidadoRepository.deleteByGrupoId(id);

        // Depois remove o grupo
        grupoConvidadoRepository.delete(grupoConvidados);
    }



    public GrupoConvidadoResponseDTO criar(
            Long casamentoId,
            CriarGrupoConvidadoDTO dto
    ){
        Casamento casamento = casamentoRepository.findById(casamentoId)
                .orElseThrow(() -> new RuntimeException("Casamento não encontrado"));

        GrupoConvidados grupo = GrupoConvidados.builder()
                .nome(dto.nome())
                .lado(dto.lado())
                .casamento(casamento)
                .build();

        GrupoConvidados salvo = grupoConvidadoRepository.save(grupo);

        return new GrupoConvidadoResponseDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getLado()
        );
    }


    public GrupoConvidadoResponseDTO atualizar(
            Long grupoId,
            CriarGrupoConvidadoDTO dto
    ){
        GrupoConvidados grupo = grupoConvidadoRepository.findById(grupoId)
                .orElseThrow(() -> new RuntimeException("Grupo não encontrado"));


        grupo.setNome(dto.nome());
        grupo.setLado(dto.lado());

        GrupoConvidados salvo = grupoConvidadoRepository.save(grupo);

        return new GrupoConvidadoResponseDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getLado()
        );
    }


    public List<GrupoConvidadoResponseDTO> listarPorCasamento(Long casamentoId) {

        casamentoRepository.findById(casamentoId)
                .orElseThrow(() -> new RuntimeException("Casamento não encontrado"));

        return grupoConvidadoRepository.findByCasamentoId(casamentoId)
                .stream()
                .map(grupo -> new GrupoConvidadoResponseDTO(
                        grupo.getId(),
                        grupo.getNome(),
                        grupo.getLado()
                ))
                .toList();


    }


}
