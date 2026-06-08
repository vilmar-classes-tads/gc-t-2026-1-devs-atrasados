package com.testsquad.crud.modules.editalmanagement.application;

import java.time.LocalDate;
import java.util.UUID;

public record AtualizarEditalCommand(
        UUID id,
        String titulo,
        String numero,
        Integer ano,
        LocalDate dataInicioSubmissao,
        LocalDate dataFimSubmissao,
        LocalDate dataInicioAvaliacao,
        LocalDate dataFimAvaliacao
) {
}
