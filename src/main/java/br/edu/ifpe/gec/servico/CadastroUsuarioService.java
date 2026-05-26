package br.edu.ifpe.gec.servico;

import br.edu.ifpe.gec.dominio.Perfil;
import br.edu.ifpe.gec.dominio.Usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class CadastroUsuarioService {
    private static final Map<String, Usuario> USUARIOS_POR_CPF = new LinkedHashMap<>();
    private static final Map<String, Usuario> USUARIOS_POR_EMAIL = new LinkedHashMap<>();

    public Usuario cadastrar(String nome, String cpf, String email, Perfil perfil) {
        Usuario usuario = new Usuario(nome, normalizarCpf(cpf), normalizarEmail(email), perfil);

        if (USUARIOS_POR_CPF.containsKey(usuario.getCpf())) {
            throw new IllegalArgumentException("Ja existe usuario cadastrado com este CPF");
        }

        if (USUARIOS_POR_EMAIL.containsKey(usuario.getEmail())) {
            throw new IllegalArgumentException("Ja existe usuario cadastrado com este e-mail");
        }

        USUARIOS_POR_CPF.put(usuario.getCpf(), usuario);
        USUARIOS_POR_EMAIL.put(usuario.getEmail(), usuario);

        return usuario;
    }

    public List<Usuario> listar() {
        return Collections.unmodifiableList(new ArrayList<>(USUARIOS_POR_CPF.values()));
    }

    public Usuario buscarPorCpf(String cpf) {
        return USUARIOS_POR_CPF.get(normalizarCpf(cpf));
    }

    public void limparCadastros() {
        USUARIOS_POR_CPF.clear();
        USUARIOS_POR_EMAIL.clear();
    }

    private String normalizarCpf(String cpf) {
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF e obrigatorio");
        }

        return cpf.replaceAll("[^0-9]", "");
    }

    private String normalizarEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("E-mail e obrigatorio");
        }

        return email.trim().toLowerCase(Locale.ROOT);
    }
}
