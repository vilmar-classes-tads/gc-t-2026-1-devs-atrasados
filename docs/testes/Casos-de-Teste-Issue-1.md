# Casos de Teste - Issue 1 (Gestão de Editais)

## CT-10: Criar edital com ano 2019 (AVL - abaixo do limite)

**ID:** CT-10  
**Título:** Tentativa de criar edital com ano anterior a 2020

**Pré-condições:** Usuário autenticado.

**Dados:** ano: 2019 | Demais campos válidos

| Passo | Ação | Resultado Esperado |
| :--- | :--- | :--- |
| 1 | Preencher ano = 2019 | Dados preenchidos |
| 2 | Enviar POST /api/editais | Status 400, "Ano deve estar entre 2020 e 2100" |

---

## CT-11: Criar edital com ano 2020 (AVL - limite exato inferior)

**ID:** CT-11  
**Título:** Criar edital com ano 2020 (limite inferior válido)

**Pré-condições:** Usuário autenticado.

**Dados:** ano: 2020 | dataInicioSubmissao: 2020-01-01 | dataFimSubmissao: 2020-01-15 | dataInicioAvaliacao: 2020-02-01 | dataFimAvaliacao: 2020-02-15

| Passo | Ação | Resultado Esperado |
| :--- | :--- | :--- |
| 1 | Preencher ano = 2020 com datas coerentes | Dados preenchidos |
| 2 | Enviar POST /api/editais | Status 201, edital criado |

---

## CT-12: Criar edital com ano 2100 (AVL - limite exato superior)

**ID:** CT-12  
**Título:** Criar edital com ano 2100 (limite superior válido)

**Pré-condições:** Usuário autenticado.

**Dados:** ano: 2100 | dataInicioSubmissao: 2100-01-01 | dataFimSubmissao: 2100-01-15 | dataInicioAvaliacao: 2100-02-01 | dataFimAvaliacao: 2100-02-15

| Passo | Ação | Resultado Esperado |
| :--- | :--- | :--- |
| 1 | Preencher ano = 2100 com datas coerentes | Dados preenchidos |
| 2 | Enviar POST /api/editais | Status 201, edital criado |

---

## CT-13: Criar edital com ano 2101 (AVL - acima do limite)

**ID:** CT-13  
**Título:** Tentativa de criar edital com ano posterior a 2100

**Pré-condições:** Usuário autenticado.

**Dados:** ano: 2101 | Demais campos válidos

| Passo | Ação | Resultado Esperado |
| :--- | :--- | :--- |
| 1 | Preencher ano = 2101 | Dados preenchidos |
| 2 | Enviar POST /api/editais | Status 400, "Ano deve estar entre 2020 e 2100" |

---

## CT-14: Datas de submissão com início após o fim (Classe Inválida - PE)

**ID:** CT-14  
**Título:** Tentativa de criar edital com dataInicioSubmissao posterior a dataFimSubmissao

**Pré-condições:** Usuário autenticado.

**Dados:** dataInicioSubmissao: 2026-03-15 | dataFimSubmissao: 2026-03-01 | Demais campos válidos

| Passo | Ação | Resultado Esperado |
| :--- | :--- | :--- |
| 1 | Preencher início da submissão após o fim | Dados preenchidos |
| 2 | Enviar POST /api/editais | Status 400, "Data de início da submissão deve ser anterior à data de fim" |

---

## CT-15: Datas de submissão iguais (AVL - limite exato inválido)

**ID:** CT-15  
**Título:** Tentativa de criar edital com dataInicioSubmissao igual a dataFimSubmissao

**Pré-condições:** Usuário autenticado.

**Dados:** dataInicioSubmissao: 2026-03-01 | dataFimSubmissao: 2026-03-01 (mesmo dia) | Demais campos válidos

| Passo | Ação | Resultado Esperado |
| :--- | :--- | :--- |
| 1 | Preencher datas iguais de submissão | Dados preenchidos |
| 2 | Enviar POST /api/editais | Status 400, "Data de início da submissão deve ser anterior à data de fim" |

---

## CT-16: Datas de submissão com 1 dia de diferença (AVL - limite exato válido)

**ID:** CT-16  
**Título:** Criar edital com 1 dia de diferença entre início e fim da submissão

**Pré-condições:** Usuário autenticado.

**Dados:** dataInicioSubmissao: 2026-03-01 | dataFimSubmissao: 2026-03-02 | dataInicioAvaliacao: 2026-03-03 | dataFimAvaliacao: 2026-03-04

| Passo | Ação | Resultado Esperado |
| :--- | :--- | :--- |
| 1 | Preencher datas com 1 dia de diferença | Dados preenchidos |
| 2 | Enviar POST /api/editais | Status 201, edital criado |

---

## CT-17: Avaliação começa antes do fim da submissão (Classe Inválida - PE)

**ID:** CT-17  
**Título:** Tentativa de criar edital com avaliação iniciando antes do fim das submissões

**Pré-condições:** Usuário autenticado.

**Dados:** dataInicioSubmissao: 2026-03-01 | dataFimSubmissao: 2026-03-15 | dataInicioAvaliacao: 2026-03-10 | dataFimAvaliacao: 2026-03-30

| Passo | Ação | Resultado Esperado |
| :--- | :--- | :--- |
| 1 | Preencher avaliação começando antes do fim da submissão | Dados preenchidos |
| 2 | Enviar POST /api/editais | Status 400, "Data de fim da submissão não pode ser posterior à data de início da avaliação" |

---

## CT-18: Criar edital com todas as datas válidas (Happy Path)

**ID:** CT-18  
**Título:** Criar edital com todas as regras de data atendidas (Caminho Feliz)

**Pré-condições:** Usuário autenticado.

**Dados:** titulo: Edital 01/2026 | numero: 01 | ano: 2026 | dataInicioSubmissao: 2026-03-01 | dataFimSubmissao: 2026-03-15 | dataInicioAvaliacao: 2026-04-01 | dataFimAvaliacao: 2026-04-15

| Passo | Ação | Resultado Esperado |
| :--- | :--- | :--- |
| 1 | Preencher todos os campos corretamente | Dados preenchidos |
| 2 | Enviar POST /api/editais | Status 201, edital criado com ID gerado |
| 3 | Buscar GET /api/editais/{id} | Status 200, dados conferem com o cadastrado |
