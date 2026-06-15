package com.testsquad.crud.modules.editalmanagement.interfaces.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EditalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String ADMIN_EMAIL = "admin.editais@ifpe.edu.br";
    private static final String ADMIN_PASSWORD = "admin123";
    @BeforeEach
    void setUp() throws Exception {
        mockMvc.perform(post("/api/staff")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "fullName": "Admin Editais",
                                  "cpf": "00011122233",
                                  "email": "admin.editais@ifpe.edu.br",
                                  "password": "admin123",
                                  "staffType": "FACULTY",
                                  "campus": "Main Campus",
                                  "educationArea": "Education",
                                  "academicDegree": "MASTERS"
                                }
                                """));
    }

    @Test
    void shouldCreateEditalAndReturn201() throws Exception {
        mockMvc.perform(post("/api/editais")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic(ADMIN_EMAIL, ADMIN_PASSWORD))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "titulo": "Edital 01/2026",
                                  "numero": "01",
                                  "ano": 2026,
                                  "dataInicioSubmissao": "2026-03-01",
                                  "dataFimSubmissao": "2026-03-15",
                                  "dataInicioAvaliacao": "2026-04-01",
                                  "dataFimAvaliacao": "2026-04-15"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.titulo").value("Edital 01/2026"))
                .andExpect(jsonPath("$.numero").value("01"))
                .andExpect(jsonPath("$.ano").value(2026))
                .andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    void shouldRequireAuthenticationToListEditais() throws Exception {
        mockMvc.perform(get("/api/editais"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldReturnBadRequestWhenSubmissaoStartAfterEnd() throws Exception {
        mockMvc.perform(post("/api/editais")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic(ADMIN_EMAIL, ADMIN_PASSWORD))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "titulo": "Edital Inválido",
                                  "numero": "01",
                                  "ano": 2026,
                                  "dataInicioSubmissao": "2026-03-15",
                                  "dataFimSubmissao": "2026-03-01",
                                  "dataInicioAvaliacao": "2026-04-01",
                                  "dataFimAvaliacao": "2026-04-15"
                                }
                                """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestWhenAvaliacaoStartAfterEnd() throws Exception {
        mockMvc.perform(post("/api/editais")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic(ADMIN_EMAIL, ADMIN_PASSWORD))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "titulo": "Edital Inválido",
                                  "numero": "01",
                                  "ano": 2026,
                                  "dataInicioSubmissao": "2026-03-01",
                                  "dataFimSubmissao": "2026-03-15",
                                  "dataInicioAvaliacao": "2026-04-15",
                                  "dataFimAvaliacao": "2026-04-01"
                                }
                                """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldListEditaisWhenAuthenticated() throws Exception {
        mockMvc.perform(post("/api/editais")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic(ADMIN_EMAIL, ADMIN_PASSWORD))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "titulo": "Edital Lista",
                                  "numero": "02",
                                  "ano": 2026,
                                  "dataInicioSubmissao": "2026-03-01",
                                  "dataFimSubmissao": "2026-03-15",
                                  "dataInicioAvaliacao": "2026-04-01",
                                  "dataFimAvaliacao": "2026-04-15"
                                }
                                """))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/editais")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic(ADMIN_EMAIL, ADMIN_PASSWORD)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.titulo=='Edital Lista')]").exists());
    }

    @Test
    void shouldReturn404WhenEditalNotFound() throws Exception {
        mockMvc.perform(get("/api/editais/550e8400-e29b-41d4-a716-446655440000")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic(ADMIN_EMAIL, ADMIN_PASSWORD)))
                .andExpect(status().isNotFound());
    }
}
