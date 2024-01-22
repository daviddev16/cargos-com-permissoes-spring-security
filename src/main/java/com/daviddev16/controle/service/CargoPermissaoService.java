package com.daviddev16.controle.service;

import com.daviddev16.controle.Permissao;
import com.daviddev16.controle.entidade.Cargo;
import com.daviddev16.controle.entidade.CargoPermissao;

import java.util.List;

public interface CargoPermissaoService {

    List<CargoPermissao> obterCargoPermissoesDeCargo( Cargo cargo );

    List<Permissao> obterPermissoesDeCargo( Cargo cargo );


}
