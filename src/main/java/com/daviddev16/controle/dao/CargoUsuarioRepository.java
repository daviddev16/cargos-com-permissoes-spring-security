package com.daviddev16.controle.dao;

import com.daviddev16.controle.entidade.CargoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CargoUsuarioRepository extends JpaRepository<CargoUsuario, Integer> {

    @Query("SELECT cg FROM CargoUsuario cg LEFT JOIN FETCH cg.cargo c WHERE cg.usuario.id = :usuarioId")
    List<CargoUsuario> findCargoUsuarioByUsuarioId( Integer usuarioId );

}
