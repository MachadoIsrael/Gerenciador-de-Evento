package com.casamento.wedding_api.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "casamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Casamento {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String nomeNoivo;

    @Column(nullable = false)
    private String nomeNoiva;

    private LocalDateTime dataEvento;

    @Column(nullable = false)
    private LocalDate dataLimiteConfirmacao;

    @Column(nullable = false)
    private Integer limiteNoivo;

    @Column(nullable = false)
    private Integer limiteNoiva;

}
