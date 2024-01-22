package com.daviddev16.controle.service;

import com.daviddev16.controle.entidade.Cargo;
import com.daviddev16.controle.entidade.CargoUsuario;
import com.daviddev16.controle.entidade.Usuario;

import java.util.List;

public interface CargoUsuarioService {

    List<CargoUsuario> obterCargoUsuarioDeUsuario( Usuario usuario );

    List<Cargo> obterCargosDeUsuario( Usuario usuario );

    void adicionarCargoAUsuario( Usuario usuario, Cargo cargo );

}
