package com.daviddev16.controle.specification;

import com.daviddev16.controle.entidade.Cargo;
import org.springframework.data.jpa.domain.Specification;

import java.util.Set;

public final class CargoSpecification {

    private CargoSpecification() {}

    public static Specification<Cargo> criarSpecCargoPorPropriedade(Set<?> conjunto, String propriedade) {
        return (root, query, criteriaBuilder) -> root.get(propriedade).in(conjunto);
    }

}
