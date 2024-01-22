package com.daviddev16.controle.dao;

import com.daviddev16.controle.entidade.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Deprecated(forRemoval = true)
    @Query("SELECT DISTINCT u FROM Usuario u LEFT JOIN FETCH u.cargos cg LEFT JOIN FETCH cg.cargo WHERE u.login = :login")
    Optional<Usuario> findByLoginFetchingCargos(@Param("login") String login);

    Optional<Usuario> findByLogin(String login);

}
