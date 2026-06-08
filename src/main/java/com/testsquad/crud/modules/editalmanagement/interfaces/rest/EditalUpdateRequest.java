package com.testsquad.crud.modules.editalmanagement.interfaces.rest;

import java.time.LocalDate;

public record EditalUpdateRequest(
        String titulo,
        String numero,
        Integer ano,
        LocalDate dataInicioSubmissao,
        LocalDate dataFimSubmissao,
        LocalDate dataInicioAvaliacao,
        LocalDate dataFimAvaliacao
) {
}
