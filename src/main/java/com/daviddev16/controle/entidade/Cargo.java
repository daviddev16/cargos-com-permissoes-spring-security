package com.daviddev16.controle.entidade;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "cargo")
public class Cargo {

    @Id
    @SequenceGenerator(
            name = "cargo_idcargo_seq",
            sequenceName = "cargo_idcargo_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "cargo_idcargo_seq",
            strategy = GenerationType.SEQUENCE
    )
    @Column(
            name = "idcargo",
            nullable = false
    )
    private Integer id;


    @Column(
            name = "nmcargo",
            nullable = false
    )
    private String nome;


    @OneToMany(mappedBy = "cargo", fetch = FetchType.EAGER)
    private List<CargoPermissao> cargoPermissoes;

    @Override
    public String toString() {
        return "Cargo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
