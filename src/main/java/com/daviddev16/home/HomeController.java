package com.daviddev16.home;

import com.daviddev16.controle.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/home")
public class HomeController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @GetMapping
    public String home(Authentication authentication) {
        return "Olá mundo: " + authentication.toString();
    }

}
