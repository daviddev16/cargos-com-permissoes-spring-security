package com.daviddev16.controle.entidade;

import com.daviddev16.controle.Permissao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(
        name = "cargo_permissoes"
)
public class CargoPermissao {

    @Id
    @SequenceGenerator(
            name = "cargo_permissoes_idcargopermissoes_seq",
            sequenceName = "cargo_permissoes_idcargopermissoes_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "cargo_permissoes_idcargopermissoes_seq",
            strategy = GenerationType.SEQUENCE
    )
    @Column(
            name = "idcargopermissoes",
            nullable = false
    )
    private Integer id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "idcargo",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_cargo_permissoes_idcargo",
                                     value = ConstraintMode.CONSTRAINT)
    )
    private Cargo cargo;

    @Column(
            name = "idpermissao",
            nullable = false
    )
    @Enumerated(EnumType.ORDINAL)
    private Permissao permissao;

    public CargoPermissao(Permissao permissao) {
        this.permissao = permissao;
    }

    public static List<CargoPermissao> criarPorPermissoes(Permissao... permissoes) {
        List<CargoPermissao> cargoPermissoes = new ArrayList<>();
        for (Permissao permissao : permissoes) {
            cargoPermissoes.add(new CargoPermissao(permissao));
        }
        return cargoPermissoes;
    }

    @Override
    public String toString() {
        return "CargoPermissao{" +
                "id=" + id +
                ", permissao=" + permissao +
                '}';
    }
}
