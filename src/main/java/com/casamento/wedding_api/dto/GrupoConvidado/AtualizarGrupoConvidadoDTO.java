package com.casamento.wedding_api.dto.GrupoConvidado;

import com.casamento.wedding_api.enums.LadoConvidado;

public record AtualizarGrupoConvidadoDTO(
        String nome,
        LadoConvidado ladoConvidado
) {
}
