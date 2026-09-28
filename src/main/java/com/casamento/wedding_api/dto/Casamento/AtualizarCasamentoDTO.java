package com.casamento.wedding_api.dto.Casamento;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AtualizarCasamentoDTO(
        String nomeNoivo,
        String nomeNoiva,
        LocalDateTime dataEvento,
        LocalDate dataLimiteConfirmacao,
        Integer limiteNoivo,
        Integer limiteNoiva
) {
}
