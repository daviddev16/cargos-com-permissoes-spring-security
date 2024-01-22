package com.daviddev16.controle.service;

import com.daviddev16.controle.entidade.Cargo;

import java.util.List;
import java.util.Set;

public interface CargoService {

    List<Cargo> obterCargoPorNomes(Set<String> nomes);

}
