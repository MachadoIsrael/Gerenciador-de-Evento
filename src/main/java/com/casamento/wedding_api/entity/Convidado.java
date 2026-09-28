package com.casamento.wedding_api.entity;


import com.casamento.wedding_api.enums.StatusPresenca;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "convidado")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Convidado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPresenca statusPresenca = StatusPresenca.PENDENTE;

    @Column(nullable = false)
    private boolean responsavel = false;

    @ManyToOne
    @JoinColumn(name = "grupo_id", nullable = false)
    private GrupoConvidados grupo;

}
