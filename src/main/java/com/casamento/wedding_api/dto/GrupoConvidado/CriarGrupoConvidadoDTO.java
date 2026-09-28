package com.casamento.wedding_api.dto.GrupoConvidado;

import com.casamento.wedding_api.enums.LadoConvidado;

public record CriarGrupoConvidadoDTO(
        String nome,
        LadoConvidado lado
) {
}
