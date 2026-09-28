package com.casamento.wedding_api.entity;


import com.casamento.wedding_api.enums.LadoConvidado;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "grupo_convidados")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GrupoConvidados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LadoConvidado lado;

    @ManyToOne
    @JoinColumn(name = "casamento_id", nullable = false)
    private Casamento casamento;
}
