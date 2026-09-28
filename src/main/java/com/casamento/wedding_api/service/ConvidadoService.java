package com.casamento.wedding_api.service;

import com.casamento.wedding_api.Exception.RegraNegocioException;
import com.casamento.wedding_api.dto.Convidado.AtualizarConvidadoDTO;
import com.casamento.wedding_api.dto.Convidado.ConfirmarPresencaDTO;
import com.casamento.wedding_api.dto.Convidado.ConvidadoResponseDTO;
import com.casamento.wedding_api.dto.Convidado.CriarConvidadoDTO;
import com.casamento.wedding_api.entity.Casamento;
import com.casamento.wedding_api.entity.Convidado;
import com.casamento.wedding_api.entity.GrupoConvidados;
import com.casamento.wedding_api.enums.StatusPresenca;
import com.casamento.wedding_api.repository.ConvidadoRepository;
import com.casamento.wedding_api.repository.GrupoConvidadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConvidadoService {

    private final ConvidadoRepository convidadoRepository;
    private final GrupoConvidadoRepository grupoConvidadoRepository;



    public List<ConvidadoResponseDTO> listaConvidados() {

        return convidadoRepository.findAll()
                .stream()
                .map(convidado -> new ConvidadoResponseDTO(
                        convidado.getId(),
                        convidado.getNome(),
                        convidado.getStatusPresenca(),
                        convidado.isResponsavel()

                )).toList();
    }


    public ConvidadoResponseDTO atualizarStatus(
            Long convidadoId,
            ConfirmarPresencaDTO dto
    ) {
        Convidado convidado = convidadoRepository.findById(convidadoId)
                .orElseThrow(() -> new RuntimeException("Convidado não encontrado"));


        Casamento casamento = convidado
                .getGrupo()
                .getCasamento();

        boolean prazoEncerrado =
                LocalDate.now().isAfter(casamento.getDataLimiteConfirmacao());


        if(!prazoEncerrado) {
            convidado.setStatusPresenca(dto.statusPresenca());
        } else {
            if(!convidado.isResponsavel()) {
                throw new RegraNegocioException(
                        "Prazo para confirmação encerrado."
                );
            }

            if(convidado.getStatusPresenca() != StatusPresenca.CONFIRMADO) {
                throw new RegraNegocioException(
                        "Prazo para confirmação encerrado."
                );
            }

            if(dto.statusPresenca() != StatusPresenca.NAO_COMPARECERA) {
                throw new RegraNegocioException(
                        "Após o prazo, só é permitido cancelar a presença."
                );
            }

            convidado.setStatusPresenca(StatusPresenca.NAO_COMPARECERA);
        }


        Convidado salvo = convidadoRepository.save(convidado);

        return new ConvidadoResponseDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getStatusPresenca(),
                salvo.isResponsavel()
        );
    }



    public void encerrarConvidado(Long casamentoId) {



    }




    public List<ConvidadoResponseDTO> listarPorGrupo(Long grupoId) {
        grupoConvidadoRepository.findById(grupoId).orElseThrow(() -> new RuntimeException("Grupo no encontrado"));

        return convidadoRepository.findByGrupoId(grupoId)
                .stream()
                .map(convidado -> new ConvidadoResponseDTO(
                        convidado.getId(),
                        convidado.getNome(),
                        convidado.getStatusPresenca(),
                        convidado.isResponsavel()
                )).toList();
    }


    public ConvidadoResponseDTO findById(Long id) {
        Convidado convidado = convidadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Convidado não encontrado"));

        return new ConvidadoResponseDTO(
                convidado.getId(),
                convidado.getNome(),
                convidado.getStatusPresenca(),
                convidado.isResponsavel()
        );
    }

    public ConvidadoResponseDTO criar(Long grupoId, CriarConvidadoDTO dto) {
        GrupoConvidados grupo = grupoConvidadoRepository.findById(grupoId)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));

        Convidado convidado = Convidado.builder()
                .nome(dto.nome())
                .responsavel(dto.responsavel())
                .statusPresenca(StatusPresenca.PENDENTE)
                .grupo(grupo)
                .build();


        Convidado salvo = convidadoRepository.save(convidado);

        return new ConvidadoResponseDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getStatusPresenca(),
                salvo.isResponsavel()
        );
    }

    public ConvidadoResponseDTO atualizar(Long grupoId, Long convidadoId, AtualizarConvidadoDTO dto) {

        GrupoConvidados grupo = grupoConvidadoRepository.findById(grupoId)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));

        Convidado convidado = convidadoRepository.findById(convidadoId)
                .orElseThrow(() -> new RuntimeException("Convidado no encontrado"));


        if(!convidado.getGrupo().getId().equals(grupo.getId())){
            throw new RuntimeException("Convidado não pertence ao grupo informado");
        }


        convidado.setNome(dto.nome());
        convidado.setResponsavel(dto.responsavel());

        Convidado salvo = convidadoRepository.save(convidado);

        return new ConvidadoResponseDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getStatusPresenca(),
                salvo.isResponsavel()
        );

    }

    public void remover(Long grupoId, Long convidadoId) {
        Convidado convidado = convidadoRepository.findById(convidadoId)
                .orElseThrow(() -> new RuntimeException("Convidado no encontrado"));


        if (!convidado.getGrupo().getId().equals(grupoId)) {
            throw new RuntimeException("Convidado não pertence a este grupo");
        }

        convidadoRepository.delete(convidado);
    }
}
