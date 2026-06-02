package com.testsquad.crud.modules.editalmanagement.infrastructure.persistence;

import com.testsquad.crud.modules.editalmanagement.domain.model.Edital;
import com.testsquad.crud.modules.editalmanagement.domain.repository.EditalRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryEditalRepository implements EditalRepository {

    private static final Map<UUID, Edital> EDITAIS = new ConcurrentHashMap<>();

    @Override
    public Edital save(Edital edital) {
        EDITAIS.put(edital.getId(), edital);
        return edital;
    }

    @Override
    public List<Edital> findAll() {
        return new ArrayList<>(EDITAIS.values());
    }

    @Override
    public Optional<Edital> findById(UUID id) {
        return Optional.ofNullable(EDITAIS.get(id));
    }

    @Override
    public boolean existsById(UUID id) {
        return EDITAIS.containsKey(id);
    }

    @Override
    public void deleteById(UUID id) {
        EDITAIS.remove(id);
    }
}
