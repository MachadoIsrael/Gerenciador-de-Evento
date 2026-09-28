package com.casamento.wedding_api.service;


import com.casamento.wedding_api.dto.Casamento.AtualizarCasamentoDTO;
import com.casamento.wedding_api.dto.Casamento.CasamentoResponseDTO;
import com.casamento.wedding_api.dto.Casamento.CriarCasamentoDTO;
import com.casamento.wedding_api.entity.Casamento;
import com.casamento.wedding_api.repository.CasamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CasamentoService {

    private final CasamentoRepository casamentoRepository;


    public CasamentoResponseDTO findById(Long id) {
        Casamento casamento = casamentoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Casamento não encontrado"));

        return new CasamentoResponseDTO(
                casamento.getId(),
                casamento.getNomeNoivo(),
                casamento.getNomeNoiva(),
                casamento.getDataEvento(),
                casamento.getLimiteNoivo(),
                casamento.getLimiteNoiva(),
                casamento.getDataLimiteConfirmacao()
        );

    }


    public CasamentoResponseDTO atualizar(Long id, AtualizarCasamentoDTO dto) {

        Casamento casamento = casamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Casamento não encontrado"));


        casamento.setNomeNoivo(dto.nomeNoivo());
        casamento.setNomeNoiva(dto.nomeNoiva());
        casamento.setDataEvento(dto.dataEvento());
        casamento.setLimiteNoivo(dto.limiteNoivo());
        casamento.setLimiteNoiva(dto.limiteNoiva());
        casamento.setDataLimiteConfirmacao(dto.dataLimiteConfirmacao());
        Casamento salvo = casamentoRepository.save(casamento);

        return new CasamentoResponseDTO(
                salvo.getId(),
                salvo.getNomeNoivo(),
                salvo.getNomeNoiva(),
                salvo.getDataEvento(),
                salvo.getLimiteNoivo(),
                salvo.getLimiteNoiva(),
                salvo.getDataLimiteConfirmacao()
        );

    }



    public CasamentoResponseDTO criar(CriarCasamentoDTO dto) {

        Casamento casamento = Casamento.builder()
                .nomeNoivo(dto.nomeNoivo())
                .nomeNoiva(dto.nomeNoiva())
                .dataEvento(dto.dataEvento())
                .limiteNoivo(dto.limiteNoivo())
                .limiteNoiva(dto.limiteNoiva())
                .dataLimiteConfirmacao(dto.dataLimiteConfirmacao())
                .build();

        Casamento salvo = casamentoRepository.save(casamento);

        return new CasamentoResponseDTO(
                salvo.getId(),
                salvo.getNomeNoivo(),
                salvo.getNomeNoiva(),
                salvo.getDataEvento(),
                salvo.getLimiteNoivo(),
                salvo.getLimiteNoiva(),
                salvo.getDataLimiteConfirmacao()
        );
    }
}
