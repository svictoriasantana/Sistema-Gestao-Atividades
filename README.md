# 🚀 Sistema de Apoio à Gestão de Atividades 

Este é o back-end (API RESTful) do Sistema de Apoio à Gestão de Atividades do Grupo de Estudos e Pesquisas em Alfabetização (GEPA).
O sistema foi desenvolvido para otimizar a organização de tarefas, gerenciamento de bolsistas e controle de projetos do grupo.

## 🛠️ Tecnologias Utilizadas

A arquitetura foi construída com foco em escalabilidade e boas práticas de Engenharia de Software:

* **Java 17+**
* **Spring Boot 3** (Web, Data JPA)
* **Banco de Dados:** MySQL
* **Gerenciador de Dependências:** Apache Maven
* **Produtividade:** Lombok (Redução de Boilerplate)
* **Documentação da API:** Swagger / OpenAPI

## ⚙️ Arquitetura do Sistema

O projeto segue o padrão arquitetural em camadas (`Layered Architecture`):
1. **Models/Entities:** Mapeamento Objeto-Relacional (ORM) com Hibernate.
2. **Repositories:** Interfaces Spring Data JPA para comunicação inteligente com o banco de dados.
3. **Services:** Camada de isolamento contendo todas as regras de negócio (ex: validação de limites de bolsistas por projeto).
4. **Controllers:** Endpoints REST que recebem requisições HTTP e retornam objetos JSON.

## 📌 Principais Funcionalidades (Endpoints)

* **Usuários:** Cadastro, listagem e separação de perfis (Coordenadora vs. Bolsista). Senhas são protegidas contra serialização JSON.
* **Projetos:** Criação de projetos e alocação de equipe (com regra de negócio limitando a 2 bolsistas por projeto).
* **Tarefas (Kanban):** Criação de tarefas com prazos e atualização de status (`PENDENTE`, `EM_ANDAMENTO`, `CONCLUIDA`).
* **Registros de Atividades:** Diário de bordo detalhado para acompanhamento do progresso das tarefas.
* **Mural de Eventos:** Controle de eventos externos organizados automaticamente pela data limite.

## 🚀 Como Executar o Projeto Localmente

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/svictoriasantana/Sistema-Gestao-Atividades
   
2. **Configure o Banco de Dados:**
   - Crie um banco de dados no MySQL chamado gepa_db.
   - Na pasta src/main/resources/, localize o arquivo application.properties.example.
   - Renomeie este arquivo para application.properties.
   - Abra o arquivo e preencha as variáveis spring.datasource.username e spring.datasource.password com as credenciais do seu MySQL local.
   
4. **Execute a aplicação:**
   Rode a classe principal GepaApplication.java na sua IDE. O servidor iniciará na porta 8080 (O Hibernate se encarregará de criar todas    as tabelas automaticamente).
   
5. **Acesse a Documentação (Swagger):**
   Com o sistema rodando, abra o navegador e acesse a interface interativa de testes:
   ```bash
   http://localhost:8080/swagger-ui/index.html
