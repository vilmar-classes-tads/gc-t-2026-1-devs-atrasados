# Changelog

Todas as mudanças relevantes deste projeto serão documentadas neste arquivo.

## [0.3.1] - 2026-07-13

### Fixed

* Validação de dígitos verificadores do CPF (algoritmo oficial da Receita Federal).
* Verificação de duplicidade de CPF/email movida para antes do hash BCrypt.
* Campo `gender` agora obrigatório na criação de usuário.
* Datas iguais de início e fim de submissão/avaliação agora são rejeitadas.
* Validação de que `dataFimSubmissao` deve ser anterior a `dataInicioAvaliacao`.
* Campo `ano` do edital agora validado entre 2020 e 2100.
* Record `ApiErrorResponse` extraído para módulo compartilhado (`common/interfaces/rest/`), eliminando duplicação.

## [0.3.0] - 2026-06-15

### Added

* Criação do `Dockerfile` para containerização da aplicação.
* Configuração de pipeline de Integração Contínua (CI) com GitHub Actions.
* Automação do processo de build utilizando Maven no ambiente de CI.
* Validação automática da aplicação através de execução em container Docker.

### Changed

* Padronização do processo de build e execução da aplicação.
* Melhorias na infraestrutura do projeto para suportar integração e entrega contínuas.

## [0.2.0] - 2026-06-15

### Added

* Implementação da classe `Projeto`.
* Criação do mecanismo de submissão de projetos.
* Bloqueio de edição de projetos após a submissão.

### Changed

* Ajustes na modelagem de entidades para suportar o gerenciamento de projetos.
* Atualização das regras de negócio relacionadas ao ciclo de vida de projetos.

## [0.1.0] - 2026-06-08

### Added

* Configuração inicial do projeto utilizando Maven.
* Estrutura base da aplicação.
* Cadastro de usuários.
* Definição automática de roles para usuários.
* Testes automatizados para funcionalidades iniciais.
* Gerenciamento de editais.
* Validação de datas dos editais.

### Changed

* Reestruturação da implementação de cadastro de usuários.
* Ajustes na arquitetura inicial do projeto.

### Fixed

* Correções identificadas durante a revisão da implementação inicial do cadastro de usuários.
