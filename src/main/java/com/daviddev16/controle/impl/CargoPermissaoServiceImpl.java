package com.daviddev16.controle.impl;

import com.daviddev16.controle.Permissao;
import com.daviddev16.controle.dao.CargoPermissaoRepository;
import com.daviddev16.controle.entidade.Cargo;
import com.daviddev16.controle.entidade.CargoPermissao;
import com.daviddev16.controle.projection.IPermissaoProjection;
import com.daviddev16.controle.service.CargoPermissaoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CargoPermissaoServiceImpl implements CargoPermissaoService {

    private final CargoPermissaoRepository cargoPermissaoRepository;

    public CargoPermissaoServiceImpl(CargoPermissaoRepository cargoPermissaoRepository) {
        this.cargoPermissaoRepository = cargoPermissaoRepository;
    }

    public List<CargoPermissao> obterCargoPermissoesDeCargo(Cargo cargo) {
        return cargoPermissaoRepository
                .findCargoPermissaoByCargoId(cargo.getId());
    }

    public List<Permissao> obterPermissoesDeCargoPorId(Integer cargoId) {
        return cargoPermissaoRepository
                .findPermissoesByCargoId(cargoId).stream()
                .map(IPermissaoProjection::getPermissao)
                .collect(Collectors.toList());
    }

    @Override
    public List<Permissao> obterPermissoesDeCargo(Cargo cargo) {
        return obterPermissoesDeCargoPorId(cargo.getId());
    }
}
