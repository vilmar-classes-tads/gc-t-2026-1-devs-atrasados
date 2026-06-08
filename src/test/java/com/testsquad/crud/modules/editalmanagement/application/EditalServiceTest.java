package com.testsquad.crud.modules.editalmanagement.application;

import com.testsquad.crud.modules.editalmanagement.application.exception.EditalNotFoundException;
import com.testsquad.crud.modules.editalmanagement.domain.model.Edital;
import com.testsquad.crud.modules.editalmanagement.domain.repository.EditalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EditalServiceTest {

    private EditalService service;

    @BeforeEach
    void setUp() {
        service = new EditalService(new EditalRepositoryFake());
    }

    @Test
    void shouldCreateEdital() {
        var edital = service.criar(command());

        assertNotNull(edital.getId());
        assertEquals("Edital 01/2026", edital.getTitulo());
        assertEquals("01", edital.getNumero());
        assertEquals(2026, edital.getAno());
    }

    @Test
    void shouldListAllEditais() {
        service.criar(command());
        service.criar(command("Edital 02/2026", "02"));

        var editais = service.listarTodos();

        assertEquals(2, editais.size());
    }

    @Test
    void shouldFindEditalById() {
        var edital = service.criar(command());

        var found = service.buscarPorId(edital.getId());

        assertEquals(edital.getId(), found.getId());
        assertEquals(edital.getTitulo(), found.getTitulo());
    }

    @Test
    void shouldThrowExceptionWhenEditalNotFound() {
        assertThrows(EditalNotFoundException.class,
                () -> service.buscarPorId(UUID.randomUUID()));
    }

    @Test
    void shouldUpdateEdital() {
        var edital = service.criar(command());

        var updated = service.atualizar(new AtualizarEditalCommand(
                edital.getId(),
                "Edital Atualizado",
                "02",
                2027,
                LocalDate.of(2027, 3, 1),
                LocalDate.of(2027, 3, 15),
                LocalDate.of(2027, 4, 1),
                LocalDate.of(2027, 4, 15)
        ));

        assertEquals(edital.getId(), updated.getId());
        assertEquals("Edital Atualizado", updated.getTitulo());
        assertEquals("02", updated.getNumero());
        assertEquals(2027, updated.getAno());
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistentEdital() {
        assertThrows(EditalNotFoundException.class,
                () -> service.atualizar(new AtualizarEditalCommand(
                        UUID.randomUUID(),
                        "Edital",
                        "01",
                        2026,
                        LocalDate.of(2026, 3, 1),
                        LocalDate.of(2026, 3, 15),
                        LocalDate.of(2026, 4, 1),
                        LocalDate.of(2026, 4, 15)
                )));
    }

    @Test
    void shouldNotAllowSubmissaoStartAfterEnd() {
        assertThrows(IllegalArgumentException.class,
                () -> service.criar(new CriarEditalCommand(
                        "Edital",
                        "01",
                        2026,
                        LocalDate.of(2026, 3, 15),
                        LocalDate.of(2026, 3, 1),
                        LocalDate.of(2026, 4, 1),
                        LocalDate.of(2026, 4, 15)
                )));
    }

    @Test
    void shouldNotAllowAvaliacaoStartAfterEnd() {
        assertThrows(IllegalArgumentException.class,
                () -> service.criar(new CriarEditalCommand(
                        "Edital",
                        "01",
                        2026,
                        LocalDate.of(2026, 3, 1),
                        LocalDate.of(2026, 3, 15),
                        LocalDate.of(2026, 4, 15),
                        LocalDate.of(2026, 4, 1)
                )));
    }

    private CriarEditalCommand command() {
        return command("Edital 01/2026", "01");
    }

    private CriarEditalCommand command(String titulo, String numero) {
        return new CriarEditalCommand(
                titulo,
                numero,
                2026,
                LocalDate.of(2026, 3, 1),
                LocalDate.of(2026, 3, 15),
                LocalDate.of(2026, 4, 1),
                LocalDate.of(2026, 4, 15)
        );
    }

    private static class EditalRepositoryFake implements EditalRepository {

        private final List<Edital> editais = new ArrayList<>();

        @Override
        public Edital save(Edital edital) {
            editais.removeIf(e -> e.getId().equals(edital.getId()));
            editais.add(edital);
            return edital;
        }

        @Override
        public List<Edital> findAll() {
            return editais;
        }

        @Override
        public Optional<Edital> findById(UUID id) {
            return editais.stream().filter(e -> e.getId().equals(id)).findFirst();
        }

        @Override
        public boolean existsById(UUID id) {
            return editais.stream().anyMatch(e -> e.getId().equals(id));
        }

        @Override
        public void deleteById(UUID id) {
            editais.removeIf(e -> e.getId().equals(id));
        }
    }
}
