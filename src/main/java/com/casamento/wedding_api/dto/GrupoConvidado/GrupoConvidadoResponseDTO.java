package com.casamento.wedding_api.dto.GrupoConvidado;

import com.casamento.wedding_api.enums.LadoConvidado;

public record GrupoConvidadoResponseDTO(
        Long id,
        String nome,
        LadoConvidado lado
) {
}
