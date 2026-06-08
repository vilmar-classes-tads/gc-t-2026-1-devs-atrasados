package com.testsquad.crud.modules.editalmanagement.interfaces.rest;

import com.testsquad.crud.modules.editalmanagement.application.AtualizarEditalCommand;
import com.testsquad.crud.modules.editalmanagement.application.CriarEditalCommand;
import com.testsquad.crud.modules.editalmanagement.application.EditalService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/editais")
public class EditalController {

    private final EditalService editalService;

    public EditalController(EditalService editalService) {
        this.editalService = editalService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EditalResponse criar(@RequestBody EditalRequest request) {
        return EditalResponse.fromDomain(
                editalService.criar(new CriarEditalCommand(
                        request.titulo(),
                        request.numero(),
                        request.ano(),
                        request.dataInicioSubmissao(),
                        request.dataFimSubmissao(),
                        request.dataInicioAvaliacao(),
                        request.dataFimAvaliacao()
                ))
        );
    }

    @GetMapping
    public List<EditalResponse> listarTodos() {
        return editalService.listarTodos()
                .stream()
                .map(EditalResponse::fromDomain)
                .toList();
    }

    @GetMapping("/{id}")
    public EditalResponse buscarPorId(@PathVariable UUID id) {
        return EditalResponse.fromDomain(editalService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public EditalResponse atualizar(@PathVariable UUID id, @RequestBody EditalUpdateRequest request) {
        return EditalResponse.fromDomain(
                editalService.atualizar(new AtualizarEditalCommand(
                        id,
                        request.titulo(),
                        request.numero(),
                        request.ano(),
                        request.dataInicioSubmissao(),
                        request.dataFimSubmissao(),
                        request.dataInicioAvaliacao(),
                        request.dataFimAvaliacao()
                ))
        );
    }
}
