package com.daviddev16.controle.impl;

import com.daviddev16.controle.dao.CargoRepository;
import com.daviddev16.controle.entidade.Cargo;
import com.daviddev16.controle.exception.InvalidRequestStateException;
import com.daviddev16.controle.service.CargoService;
import com.daviddev16.controle.specification.CargoSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CargoServiceImpl implements CargoService {

    private CargoRepository cargoRepository;

    public CargoServiceImpl(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    @Override
    public List<Cargo> obterCargoPorNomes(Set<String> nomes) {

        if (nomes == null || nomes.isEmpty())
            throw new InvalidRequestStateException("Nomes dos cargos não informado.");

        Specification<Cargo> cargoNomesSpec = CargoSpecification
                .criarSpecCargoPorPropriedade(nomes, "nome");

        return cargoRepository.findAll(cargoNomesSpec);
    }
}
