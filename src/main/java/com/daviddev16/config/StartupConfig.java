package com.daviddev16.config;

import com.daviddev16.annotation.DevEnvironmentConfig;
import com.daviddev16.controle.Permissao;
import com.daviddev16.controle.builder.UsuarioEncarregadoBuilder;
import com.daviddev16.controle.dao.CargoPermissaoRepository;
import com.daviddev16.controle.dao.CargoRepository;
import com.daviddev16.controle.dao.CargoUsuarioRepository;
import com.daviddev16.controle.entidade.Cargo;
import com.daviddev16.controle.entidade.CargoPermissao;
import com.daviddev16.controle.entidade.CargoUsuario;
import com.daviddev16.controle.entidade.Usuario;
import com.daviddev16.controle.impl.UsuarioServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@DevEnvironmentConfig
public class StartupConfig {

    private final CargoRepository cargoRepository;
    private final CargoPermissaoRepository cargoPermissaoRepository;
    private final CargoUsuarioRepository cargoUsuarioRepository;
    private final UsuarioServiceImpl usuarioService;

    public StartupConfig(CargoRepository cargoRepository,
                         CargoPermissaoRepository cargoPermissaoRepository,
                         CargoUsuarioRepository cargoUsuarioRepository,
                         UsuarioServiceImpl usuarioService)
    {
        this.cargoRepository = cargoRepository;
        this.cargoPermissaoRepository = cargoPermissaoRepository;
        this.cargoUsuarioRepository = cargoUsuarioRepository;
        this.usuarioService = usuarioService;
    }

    @Bean
    @Transactional
    CommandLineRunner commandLineRunner() {
        return (args) ->
        {
            /* CARGOS E PERMISSÕES */

            List<CargoPermissao> permissoesOperador = CargoPermissao
                    .criarPorPermissoes(Permissao.ESCRITA_LIMITADA, Permissao.LEITURA_LIMITADA);

            List<CargoPermissao> permissoesAdministrador = CargoPermissao
                    .criarPorPermissoes(Permissao.ESCRITA_GERAL, Permissao.LEITURA_GERAL);

            Cargo cargoOperador = Cargo.builder()
                    .nome("OPERADOR")
                    .cargoPermissoes(permissoesOperador)
                    .build();

            Cargo cargoVisualizador = Cargo.builder()
                    .nome("VISUALIZADOR")
                    .cargoPermissoes(permissoesOperador)
                    .build();

            Cargo cargoAdministrador = Cargo.builder()
                    .nome("ADMINISTRADOR")
                    .cargoPermissoes(permissoesAdministrador)
                    .build();

            cargoRepository.save(cargoOperador);
            cargoRepository.save(cargoVisualizador);
            cargoRepository.save(cargoAdministrador);

            permissoesOperador.forEach(cargoPermissao ->
            {
                cargoPermissao.setCargo(cargoOperador);
                cargoPermissaoRepository.save(cargoPermissao);
            });

            permissoesAdministrador.forEach(cargoPermissao ->
            {
                cargoPermissao.setCargo(cargoAdministrador);
                cargoPermissaoRepository.save(cargoPermissao);
            });

            usuarioService.criarUsuarioDeUsuarioEncarregado(UsuarioEncarregadoBuilder
                    .builder()
                            .cargos("ADMINISTRADOR", "OPERADOR")
                            .usuarioSimples("spring", "Spring Usuário Adm", "123")
                    .build());

            usuarioService.criarUsuarioDeUsuarioEncarregado(UsuarioEncarregadoBuilder
                    .builder()
                    .cargos("VISUALIZADOR")
                    .usuarioSimples("comum", "Usuário comum", "123")
                    .build());

        };
    }

}
