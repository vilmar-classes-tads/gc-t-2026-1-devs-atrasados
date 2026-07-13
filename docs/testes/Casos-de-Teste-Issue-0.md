# Casos de Teste - Issue 0 (Cadastro de Servidores)

## CT-01: Cadastro com CPF de dígitos verificadores inválidos (Classe Inválida - PE)

**ID:** CT-01  
**Título:** Tentativa de cadastro com CPF contendo dígitos verificadores inválidos

**Pré-condições:** Sistema acessível. Nenhum usuário cadastrado com o CPF a ser testado.

**Dados:** Nome: Teste CPF | CPF: 111.111.111-11 | Email: teste@ifpe.edu.br | Senha: secret123 | Tipo: FACULTY | Campus: Centro | Área: TI | Grau: MASTERS | Gênero: OTHER

| Passo | Ação | Resultado Esperado |
| :--- | :--- | :--- |
| 1 | Preencher formulário com CPF de todos os dígitos iguais | Dados preenchidos |
| 2 | Enviar POST /api/staff | Status 400, mensagem "CPF is invalid" |

---

## CT-02: CPF com 10 dígitos (AVL - abaixo do limite)

**ID:** CT-02  
**Título:** Tentativa de cadastro com CPF de 10 dígitos

**Pré-condições:** Sistema acessível.

**Dados:** CPF: 123.456.789-0 (10 dígitos) | Demais campos válidos

| Passo | Ação | Resultado Esperado |
| :--- | :--- | :--- |
| 1 | Preencher CPF com 10 dígitos | Dados preenchidos |
| 2 | Enviar POST /api/staff | Status 400, "CPF must contain 11 digits" |

---

## CT-03: CPF com 12 dígitos (AVL - acima do limite)

**ID:** CT-03  
**Título:** Tentativa de cadastro com CPF de 12 dígitos

**Pré-condições:** Sistema acessível.

**Dados:** CPF: 123.456.789-012 (12 dígitos) | Demais campos válidos

| Passo | Ação | Resultado Esperado |
| :--- | :--- | :--- |
| 1 | Preencher CPF com 12 dígitos | Dados preenchidos |
| 2 | Enviar POST /api/staff | Status 400, "CPF must contain 11 digits" |

---

## CT-04: Cadastro com CPF válido (Classe Válida - PE)

**ID:** CT-04  
**Título:** Cadastro bem-sucedido com CPF válido de 11 dígitos

**Pré-condições:** Sistema acessível. CPF e email não cadastrados.

**Dados:** CPF: 390.533.447-05 | Email: maria@ifpe.edu.br | Demais campos válidos

| Passo | Ação | Resultado Esperado |
| :--- | :--- | :--- |
| 1 | Preencher todos os campos obrigatórios corretamente | Dados preenchidos |
| 2 | Enviar POST /api/staff | Status 201, "Registration completed successfully" |

---

## CT-05: Email não institucional (Classe Inválida - PE)

**ID:** CT-05  
**Título:** Tentativa de cadastro com email fora do domínio @ifpe.edu.br

**Pré-condições:** Sistema acessível.

**Dados:** Email: teste@gmail.com | Demais campos válidos

| Passo | Ação | Resultado Esperado |
| :--- | :--- | :--- |
| 1 | Preencher email @gmail.com | Dados preenchidos |
| 2 | Enviar POST /api/staff | Status 400, "Email must use the institutional domain @ifpe.edu.br" |

---

## CT-06: Senha com 5 caracteres (AVL - abaixo do limite)

**ID:** CT-06  
**Título:** Tentativa de cadastro com senha de 5 caracteres

**Pré-condições:** Sistema acessível.

**Dados:** Senha: 12345 (5 caracteres) | Demais campos válidos

| Passo | Ação | Resultado Esperado |
| :--- | :--- | :--- |
| 1 | Preencher senha com 5 caracteres | Dados preenchidos |
| 2 | Enviar POST /api/staff | Status 400, "Password must contain at least 6 characters" |

---

## CT-07: Senha com 6 caracteres (AVL - limite exato)

**ID:** CT-07  
**Título:** Cadastro com senha de exatamente 6 caracteres

**Pré-condições:** Sistema acessível. CPF e email não cadastrados.

**Dados:** Senha: 123456 (6 caracteres) | Demais campos válidos

| Passo | Ação | Resultado Esperado |
| :--- | :--- | :--- |
| 1 | Preencher senha com 6 caracteres | Dados preenchidos |
| 2 | Enviar POST /api/staff | Status 201, cadastro realizado |

---

## CT-08: Cadastro sem campo gender (Classe Inválida - PE)

**ID:** CT-08  
**Título:** Tentativa de cadastro sem informar o campo gênero

**Pré-condições:** Sistema acessível.

**Dados:** Gênero: não informado | Demais campos válidos

| Passo | Ação | Resultado Esperado |
| :--- | :--- | :--- |
| 1 | Preencher formulário sem o campo gender | Dados preenchidos |
| 2 | Enviar POST /api/staff | Status 400, "Gender is required" |

---

## CT-09: CPF duplicado (Classe Inválida - PE)

**ID:** CT-09  
**Título:** Tentativa de cadastro com CPF já existente no sistema

**Pré-condições:** Usuário já cadastrado com CPF 390.533.447-05.

**Dados:** CPF: 390.533.447-05 (já existente) | Email: novo@ifpe.edu.br (diferente)

| Passo | Ação | Resultado Esperado |
| :--- | :--- | :--- |
| 1 | Preencher com CPF duplicado | Dados preenchidos |
| 2 | Enviar POST /api/staff | Status 400, "CPF already registered: 39053344705" |
