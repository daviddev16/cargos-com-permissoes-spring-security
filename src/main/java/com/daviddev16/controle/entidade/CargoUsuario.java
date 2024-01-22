package com.daviddev16.controle.entidade;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(
        name = "cargo_usuario"
)
public class CargoUsuario {

    @Id
    @SequenceGenerator(
            name = "cargo_usuario_idcargousuario_seq",
            sequenceName = "cargo_usuario_idcargousuario_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "cargo_usuario_idcargousuario_seq",
            strategy = GenerationType.SEQUENCE
    )
    @Column(
            name = "idcargousuario",
            nullable = false
    )
    private Integer id;

    @ManyToOne
    @JoinColumn(
            name = "idcargo",
            nullable = false
    )
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(
            name = "idusuario",
            nullable = false
    )
    private Usuario usuario;

    @Override
    public String toString() {
        return "CargoUsuario{" +
                "id=" + id +
                '}';
    }
}
