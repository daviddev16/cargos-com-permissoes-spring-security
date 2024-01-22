package com.daviddev16.controle.impl;

import com.daviddev16.controle.dao.CargoUsuarioRepository;
import com.daviddev16.controle.entidade.Cargo;
import com.daviddev16.controle.entidade.CargoPermissao;
import com.daviddev16.controle.entidade.CargoUsuario;
import com.daviddev16.controle.entidade.Usuario;
import com.daviddev16.controle.service.CargoPermissaoService;
import com.daviddev16.controle.service.CargoUsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CargoUsuarioServiceImpl implements CargoUsuarioService {

    private final CargoUsuarioRepository cargoUsuarioRepository;
    private final CargoPermissaoService cargoPermissaoService;

    public CargoUsuarioServiceImpl(CargoUsuarioRepository cargoUsuarioRepository,
                                   CargoPermissaoService cargoPermissaoService)
    {
        this.cargoUsuarioRepository = cargoUsuarioRepository;
        this.cargoPermissaoService = cargoPermissaoService;
    }

    public List<CargoUsuario> obterCargoUsuarioDeUsuario(Usuario usuario) {
        List<CargoUsuario> cargoUsuarios = cargoUsuarioRepository.findCargoUsuarioByUsuarioId(usuario.getId());
        cargoUsuarios.forEach(cargoUsuario -> carregarPermissoesEmCargo(cargoUsuario.getCargo()));
        return cargoUsuarios;
    }

    private void carregarPermissoesEmCargo(Cargo cargo) {
        List<CargoPermissao> cargoPermissoes = cargoPermissaoService.obterCargoPermissoesDeCargo(cargo);
        cargo.setCargoPermissoes(cargoPermissoes);
    }

    public List<Cargo> obterCargosDeUsuario(Usuario usuario) {
        return obterCargoUsuarioDeUsuario(usuario)
                .stream()
                .map(CargoUsuario::getCargo)
                .collect(Collectors.toList());
    }

    @Override
    public void adicionarCargoAUsuario(Usuario usuario, Cargo cargo) {
        CargoUsuario cargoUsuario = new CargoUsuario();
        cargoUsuario.setUsuario(usuario);
        cargoUsuario.setCargo(cargo);
        cargoUsuarioRepository.save(cargoUsuario);
    }
}
