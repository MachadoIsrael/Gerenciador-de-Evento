package com.casamento.wedding_api.dto.Casamento;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CasamentoResponseDTO(
        Long id,
        String nomeNoivo,
        String nomeNoiva,
        LocalDateTime dataEvento,
        Integer limiteNoivo,
        Integer limiteNoiva,
        LocalDate dataLimiteConfirmacao
) {
}
