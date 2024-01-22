package com.daviddev16.controle.dao;

import com.daviddev16.controle.entidade.CargoPermissao;
import com.daviddev16.controle.projection.IPermissaoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargoPermissaoRepository extends JpaRepository<CargoPermissao, Integer> {

    @Query(" SELECT c FROM CargoPermissao c WHERE c.cargo.id = :cargoId ")
    List<CargoPermissao> findCargoPermissaoByCargoId(Integer cargoId);

    @Query(" SELECT c.permissao FROM CargoPermissao c WHERE c.cargo.id = :cargoId ")
    List<IPermissaoProjection> findPermissoesByCargoId(Integer cargoId);

}
