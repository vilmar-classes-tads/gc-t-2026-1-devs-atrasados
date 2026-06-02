package com.testsquad.crud.modules.editalmanagement.domain.repository;

import com.testsquad.crud.modules.editalmanagement.domain.model.Edital;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EditalRepository {

    Edital save(Edital edital);

    List<Edital> findAll();

    Optional<Edital> findById(UUID id);

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
