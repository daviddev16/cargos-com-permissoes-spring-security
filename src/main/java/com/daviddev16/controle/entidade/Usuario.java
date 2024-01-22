package com.daviddev16.controle.entidade;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(
        name = "usuario",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_usuario_login", columnNames = "login")}
)
public class Usuario {

    @Id
    @SequenceGenerator(
            name = "usuario_idusuario_seq",
            sequenceName = "usuario_idusuario_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "usuario_idusuario_seq",
            strategy = GenerationType.SEQUENCE
    )
    @Column(
            name = "idusuario",
            nullable = false
    )
    private Integer id;


    @Column(
            name = "nmusuario",
            nullable = false
    )
    private String nome;


    @Column(
            name = "senha",
            nullable = false
    )
    private String senha;


    @Column(
            name = "login",
            nullable = false
    )
    private String login;

    @OneToMany(mappedBy = "usuario")
    private List<CargoUsuario> cargos;

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", senha='" + senha + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
