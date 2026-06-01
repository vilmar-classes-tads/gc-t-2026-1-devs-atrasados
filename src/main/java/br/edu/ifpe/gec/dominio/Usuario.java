package br.edu.ifpe.gec.dominio;

import java.util.Objects;

public class Usuario {
    private final String nome;
    private final String cpf;
    private final String email;
    private final Perfil perfil;

    public Usuario(String nome, String cpf, String email, Perfil perfil) {
        this.nome = validarCampoObrigatorio(nome, "Nome");
        this.cpf = validarCampoObrigatorio(cpf, "CPF");
        this.email = validarCampoObrigatorio(email, "E-mail");
        this.perfil = Objects.requireNonNull(perfil, "Perfil e obrigatorio");
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    private static String validarCampoObrigatorio(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(campo + " e obrigatorio");
        }

        return valor.trim();
    }
}
