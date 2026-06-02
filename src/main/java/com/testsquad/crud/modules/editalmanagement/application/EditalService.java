package com.testsquad.crud.modules.editalmanagement.application;

import com.testsquad.crud.modules.editalmanagement.application.exception.EditalNotFoundException;
import com.testsquad.crud.modules.editalmanagement.domain.model.Edital;
import com.testsquad.crud.modules.editalmanagement.domain.repository.EditalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EditalService {

    private final EditalRepository editalRepository;

    public EditalService(EditalRepository editalRepository) {
        this.editalRepository = editalRepository;
    }

    public Edital criar(CriarEditalCommand command) {
        Edital edital = Edital.create(
                command.titulo(),
                command.numero(),
                command.ano(),
                command.dataInicioSubmissao(),
                command.dataFimSubmissao(),
                command.dataInicioAvaliacao(),
                command.dataFimAvaliacao()
        );

        return editalRepository.save(edital);
    }

    public Edital atualizar(AtualizarEditalCommand command) {
        if (!editalRepository.existsById(command.id())) {
            throw new EditalNotFoundException(command.id().toString());
        }

        Edital edital = new Edital(
                command.id(),
                command.titulo(),
                command.numero(),
                command.ano(),
                command.dataInicioSubmissao(),
                command.dataFimSubmissao(),
                command.dataInicioAvaliacao(),
                command.dataFimAvaliacao()
        );

        return editalRepository.save(edital);
    }

    public List<Edital> listarTodos() {
        return editalRepository.findAll();
    }

    public Edital buscarPorId(UUID id) {
        return editalRepository.findById(id)
                .orElseThrow(() -> new EditalNotFoundException(id.toString()));
    }
}
