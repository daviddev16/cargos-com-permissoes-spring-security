package com.daviddev16.controle.security;

import com.daviddev16.controle.entidade.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUsuarioDetails implements UserDetails {

    private final String login;
    private final String senha;
    private final Boolean isAccountNonExpired;
    private final Boolean isAccountNonLocked;
    private final Boolean isCredentialsNonExpired;
    private final Boolean isEnabled;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUsuarioDetails(String login, String senha,
                                Collection<? extends GrantedAuthority> authorities)
    {
        this(login, senha, true, authorities);
    }

    public CustomUsuarioDetails(final Usuario usuario,
                                Collection<? extends GrantedAuthority> authorities)
    {
        this(usuario.getLogin(), usuario.getSenha(), authorities);
    }

    public CustomUsuarioDetails(String login, String senha, Boolean isEnabled,
                                Collection<? extends GrantedAuthority> authorities)
    {
        this.login = login;
        this.senha = senha;
        this.isEnabled = isEnabled;
        this.isAccountNonExpired = true;
        this.isCredentialsNonExpired = true;
        this.isAccountNonLocked = true;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

}
