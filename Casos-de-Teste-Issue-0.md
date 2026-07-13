# Casos de Teste - Issue 0 (Cadastro de Servidores)

## CT-01: Cadastro com CPF de dígitos verificadores inválidos

**ID:** CT-01

**Título:** Tentativa de cadastro com CPF contendo dígitos verificadores inválidos (Classe Inválida - PE)

**Pré-condições:**
- O sistema está acessível
- Nenhum usuário cadastrado com o CPF a ser testado

**Dados:**
- Nome: Teste CPF
- CPF: 111.111.111-11
- Email: teste@ifpe.edu.br
- Senha: secret123
- Tipo: FACULTY
- Campus: Campus Centro
- Área: Tecnologia
- Grau: MASTERS
- Gênero: OTHER

| Passo | Ação/Passos | Resultado Esperado |
| :--- | :--- | :--- |
| 1 | Preencher o formulário com CPF de todos os dígitos iguais | Dados preenchidos |
| 2 | Enviar requisição POST /api/staff | O sistema rejeita o cadastro com status 400 e mensagem "CPF is invalid" |

---

## CT-02: Cadastro com CPF de 10 dígitos (AVL - abaixo do limite)

**ID:** CT-02

**Título:** Tentativa de cadastro com CPF de 10 dígitos (Valor Limite - abaixo de 11)

**Pré-condições:**
- O sistema está acessível

**Dados:**
- Nome: Teste AVL
- CPF: 123.456.789-0 (10 dígitos)
- Email: testeavl@ifpe.edu.br
- Senha: secret123
- Tipo: FACULTY
- Campus: Campus Centro
- Área: Tecnologia
- Grau: MASTERS
- Gênero: OTHER

| Passo | Ação/Passos | Resultado Esperado |
| :--- | :--- | :--- |
| 1 | Preencher o formulário com CPF de 10 dígitos | Dados preenchidos |
| 2 | Enviar requisição POST /api/staff | O sistema rejeita com status 400 e mensagem "CPF must contain 11 digits" |

---

## CT-03: Cadastro com CPF de 12 dígitos (AVL - acima do limite)

**ID:** CT-03

**Título:** Tentativa de cadastro com CPF de 12 dígitos (Valor Limite - acima de 11)

**Pré-condições:**
- O sistema está acessível

**Dados:**
- Nome: Teste AVL 2
- CPF: 123.456.789-012 (12 dígitos)
- Email: testeavl2@ifpe.edu.br
- Senha: secret123
- Tipo: FACULTY
- Campus: Campus Centro
- Área: Tecnologia
- Grau: MASTERS
- Gênero: OTHER

| Passo | Ação/Passos | Resultado Esperado |
| :--- | :--- | :--- |
| 1 | Preencher o formulário com CPF de 12 dígitos | Dados preenchidos |
| 2 | Enviar requisição POST /api/staff | O sistema rejeita com status 400 e mensagem "CPF must contain 11 digits" |

---

## CT-04: Cadastro com CPF de 11 dígitos válidos (Classe Válida - PE)

**ID:** CT-04

**Título:** Cadastro bem-sucedido com CPF válido de 11 dígitos (Classe Válida)

**Pré-condições:**
- O sistema está acessível
- CPF e email não cadastrados anteriormente

**Dados:**
- Nome: Maria Silva
- CPF: 390.533.447-05
- Email: maria.silva@ifpe.edu.br
- Senha: secret123
- Tipo: FACULTY
- Campus: Campus Centro
- Área: Tecnologia
- Grau: MASTERS
- Gênero: OTHER

| Passo | Ação/Passos | Resultado Esperado |
| :--- | :--- | :--- |
| 1 | Preencher todos os campos obrigatórios com dados válidos | Dados preenchidos |
| 2 | Enviar requisição POST /api/staff | O sistema cadastra com sucesso, retorna status 201 e mensagem "Registration completed successfully" |

---

## CT-05: Cadastro com email não institucional (Classe Inválida - PE)

**ID:** CT-05

**Título:** Tentativa de cadastro com email fora do domínio @ifpe.edu.br

**Pré-condições:**
- O sistema está acessível

**Dados:**
- Nome: Email Teste
- CPF: 390.533.447-05
- Email: teste@gmail.com
- Senha: secret123
- Tipo: FACULTY
- Campus: Campus Centro
- Área: Tecnologia
- Grau: MASTERS
- Gênero: OTHER

| Passo | Ação/Passos | Resultado Esperado |
| :--- | :--- | :--- |
| 1 | Preencher formulário com email @gmail.com | Dados preenchidos |
| 2 | Enviar requisição POST /api/staff | O sistema rejeita com status 400 e mensagem "Email must use the institutional domain @ifpe.edu.br" |

---

## CT-06: Cadastro com senha de 5 caracteres (AVL - abaixo do limite)

**ID:** CT-06

**Título:** Tentativa de cadastro com senha de 5 caracteres (Valor Limite - abaixo de 6)

**Pré-condições:**
- O sistema está acessível

**Dados:**
- Nome: Senha Teste
- CPF: 390.533.447-05
- Email: senha@ifpe.edu.br
- Senha: 12345 (5 caracteres)
- Tipo: FACULTY
- Campus: Campus Centro
- Área: Tecnologia
- Grau: MASTERS
- Gênero: OTHER

| Passo | Ação/Passos | Resultado Esperado |
| :--- | :--- | :--- |
| 1 | Preencher formulário com senha de 5 caracteres | Dados preenchidos |
| 2 | Enviar requisição POST /api/staff | O sistema rejeita com status 400 e mensagem "Password must contain at least 6 characters" |

---

## CT-07: Cadastro com senha de 6 caracteres (AVL - limite exato)

**ID:** CT-07

**Título:** Cadastro com senha de exatamente 6 caracteres (Valor Limite válido)

**Pré-condições:**
- O sistema está acessível
- CPF e email não cadastrados

**Dados:**
- Nome: Senha Ok
- CPF: 390.533.447-05
- Email: senhaok@ifpe.edu.br
- Senha: 123456 (6 caracteres)
- Tipo: FACULTY
- Campus: Campus Centro
- Área: Tecnologia
- Grau: MASTERS
- Gênero: OTHER

| Passo | Ação/Passos | Resultado Esperado |
| :--- | :--- | :--- |
| 1 | Preencher formulário com senha de 6 caracteres | Dados preenchidos |
| 2 | Enviar requisição POST /api/staff | O sistema cadastra com sucesso, status 201 |

---

## CT-08: Cadastro sem campo gender (Classe Inválida - PE)

**ID:** CT-08

**Título:** Tentativa de cadastro sem informar o campo gênero

**Pré-condições:**
- O sistema está acessível

**Dados:**
- Nome: Sem Genero
- CPF: 390.533.447-05
- Email: semgenero@ifpe.edu.br
- Senha: secret123
- Tipo: FACULTY
- Campus: Campus Centro
- Área: Tecnologia
- Grau: MASTERS
- Gênero: (não informado)

| Passo | Ação/Passos | Resultado Esperado |
| :--- | :--- | :--- |
| 1 | Preencher formulário sem o campo gender | Dados preenchidos |
| 2 | Enviar requisição POST /api/staff | O sistema rejeita com status 400 e mensagem "Gender is required" |

---

## CT-09: Cadastro com CPF duplicado (Classe Inválida - PE)

**ID:** CT-09

**Título:** Tentativa de cadastro com CPF já existente no sistema

**Pré-condições:**
- Usuário já cadastrado com CPF 390.533.447-05 e email original@ifpe.edu.br

**Dados:**
- Nome: Duplicado CPF
- CPF: 390.533.447-05 (mesmo CPF do existente)
- Email: novocpf@ifpe.edu.br (email diferente)
- Senha: secret123
- Tipo: FACULTY
- Campus: Campus Centro
- Área: Tecnologia
- Grau: MASTERS
- Gênero: OTHER

| Passo | Ação/Passos | Resultado Esperado |
| :--- | :--- | :--- |
| 1 | Preencher formulário com CPF já cadastrado | Dados preenchidos |
| 2 | Enviar requisição POST /api/staff | O sistema rejeita com status 400 e mensagem "CPF already registered: 39053344705" |
