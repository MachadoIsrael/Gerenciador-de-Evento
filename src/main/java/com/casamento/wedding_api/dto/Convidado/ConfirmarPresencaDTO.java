package com.casamento.wedding_api.dto.Convidado;

import com.casamento.wedding_api.enums.StatusPresenca;

public record ConfirmarPresencaDTO(
        StatusPresenca statusPresenca
) {
}
