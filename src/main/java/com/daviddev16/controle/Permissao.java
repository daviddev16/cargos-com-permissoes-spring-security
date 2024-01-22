package com.daviddev16.controle;

public enum Permissao {

    LEITURA_GERAL("read:geral"),
    ESCRITA_GERAL("write:geral"),

    LEITURA_LIMITADA("read:limitada"),
    ESCRITA_LIMITADA("write:limitada");

    private final String permission;

    Permissao(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

}
