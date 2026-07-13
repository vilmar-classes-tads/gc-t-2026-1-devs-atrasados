package com.testsquad.crud.modules.editalmanagement.domain.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Edital {

    private final UUID id;
    private final String titulo;
    private final String numero;
    private final Integer ano;
    private final LocalDate dataInicioSubmissao;
    private final LocalDate dataFimSubmissao;
    private final LocalDate dataInicioAvaliacao;
    private final LocalDate dataFimAvaliacao;

    public Edital(
            UUID id,
            String titulo,
            String numero,
            Integer ano,
            LocalDate dataInicioSubmissao,
            LocalDate dataFimSubmissao,
            LocalDate dataInicioAvaliacao,
            LocalDate dataFimAvaliacao
    ) {
        this.id = Objects.requireNonNull(id, "Id is required");
        this.titulo = validarObrigatorio(titulo, "Título é obrigatório");
        this.numero = validarObrigatorio(numero, "Número é obrigatório");
        this.ano = Objects.requireNonNull(ano, "Ano é obrigatório");
        if (ano < 2020 || ano > 2100) {
            throw new IllegalArgumentException("Ano deve estar entre 2020 e 2100");
        }
        this.dataInicioSubmissao = Objects.requireNonNull(dataInicioSubmissao, "Data de início da submissão é obrigatória");
        this.dataFimSubmissao = Objects.requireNonNull(dataFimSubmissao, "Data de fim da submissão é obrigatória");
        this.dataInicioAvaliacao = Objects.requireNonNull(dataInicioAvaliacao, "Data de início da avaliação é obrigatória");
        this.dataFimAvaliacao = Objects.requireNonNull(dataFimAvaliacao, "Data de fim da avaliação é obrigatória");

        if (!dataInicioSubmissao.isBefore(dataFimSubmissao)) {
            throw new IllegalArgumentException("Data de início da submissão deve ser anterior à data de fim");
        }

        if (!dataInicioAvaliacao.isBefore(dataFimAvaliacao)) {
            throw new IllegalArgumentException("Data de início da avaliação deve ser anterior à data de fim");
        }

        if (dataFimSubmissao.isAfter(dataInicioAvaliacao)) {
            throw new IllegalArgumentException("Data de fim da submissão não pode ser posterior à data de início da avaliação");
        }
    }

    public static Edital create(
            String titulo,
            String numero,
            Integer ano,
            LocalDate dataInicioSubmissao,
            LocalDate dataFimSubmissao,
            LocalDate dataInicioAvaliacao,
            LocalDate dataFimAvaliacao
    ) {
        return new Edital(
                UUID.randomUUID(),
                titulo,
                numero,
                ano,
                dataInicioSubmissao,
                dataFimSubmissao,
                dataInicioAvaliacao,
                dataFimAvaliacao
        );
    }

    public UUID getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getNumero() {
        return numero;
    }

    public Integer getAno() {
        return ano;
    }

    public LocalDate getDataInicioSubmissao() {
        return dataInicioSubmissao;
    }

    public LocalDate getDataFimSubmissao() {
        return dataFimSubmissao;
    }

    public LocalDate getDataInicioAvaliacao() {
        return dataInicioAvaliacao;
    }

    public LocalDate getDataFimAvaliacao() {
        return dataFimAvaliacao;
    }

    private String validarObrigatorio(String value, String mensagem) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(mensagem);
        }
        return value.trim();
    }
}
