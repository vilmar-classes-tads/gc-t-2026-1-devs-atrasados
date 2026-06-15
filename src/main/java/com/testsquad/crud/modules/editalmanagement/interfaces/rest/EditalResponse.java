package com.testsquad.crud.modules.editalmanagement.interfaces.rest;

import com.testsquad.crud.modules.editalmanagement.domain.model.Edital;

import java.time.LocalDate;
import java.util.UUID;

public record EditalResponse(
        UUID id,
        String titulo,
        String numero,
        Integer ano,
        LocalDate dataInicioSubmissao,
        LocalDate dataFimSubmissao,
        LocalDate dataInicioAvaliacao,
        LocalDate dataFimAvaliacao
) {
    public static EditalResponse fromDomain(Edital edital) {
        return new EditalResponse(
                edital.getId(),
                edital.getTitulo(),
                edital.getNumero(),
                edital.getAno(),
                edital.getDataInicioSubmissao(),
                edital.getDataFimSubmissao(),
                edital.getDataInicioAvaliacao(),
                edital.getDataFimAvaliacao()
        );
    }
}
