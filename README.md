# API de Tarefas — Formação de Backend In-Nova

API REST de uma lista de tarefas para aprender Spring Boot com PostgreSQL, **sem Docker**.

**Stack:** Java 17+ · Spring Boot 4.1 · Spring Data JPA · PostgreSQL

## 1. Pré-requisitos

- **JDK 17 ou superior** (`java -version`). No IntelliJ: *File → Project Structure → SDK → Download JDK*.
- **IntelliJ IDEA** (já traz Maven) — ou Maven 3.6.3+ para usar no terminal.
- **PostgreSQL**: na formação usas o do formador, pela rede local. Em casa, instala o teu (secção 6).

## 2. Ligar à base de dados

O `src/main/resources/application.yml` lê estas variáveis de ambiente:

| Variável | Por omissão | Na formação |
|---|---|---|
| `DB_HOST` | `localhost` | IP do PC do formador |
| `DB_NAME` | `tarefas` | igual (todos usam a mesma) |
| `DB_USER` / `DB_PASSWORD` | `formando` / `formando` | igual |

No IntelliJ: *Run → Edit Configurations → TasksApplication → Environment variables* →
`DB_HOST=192.168.1.50`. Também podes mudar os valores por omissão no `application.yml`.

## 3. Correr

**IntelliJ:** abre a pasta do projeto, espera que o Maven descarregue as dependências e corre `TasksApplication` (▶).

**Terminal:**

```bash
# macOS / Linux
DB_HOST=192.168.1.50 mvn spring-boot:run

# Windows (PowerShell)
$env:DB_HOST="192.168.1.50"; mvn spring-boot:run
```

A API fica em `http://localhost:8080/api/tasks` e a tabela `tasks` é criada no arranque.
Para experimentar, cria os pedidos no Postman com os endpoints da secção 4. No POST e no PUT,
escolhe *Body → raw → JSON*, por exemplo:

```json
{ "title": "Preparar formação", "description": "Slides + demo" }
```

## 4. Endpoints

| Método | URL | O que faz | Respostas |
|---|---|---|---|
| `GET` | `/api/tasks` | Lista as tarefas | 200 |
| `GET` | `/api/tasks/{id}` | Uma tarefa | 200 · 404 |
| `POST` | `/api/tasks` | Cria uma tarefa | 201 · 400 |
| `PUT` | `/api/tasks/{id}` | Substitui uma tarefa inteira | 200 · 400 · 404 |
| `DELETE` | `/api/tasks/{id}` | **Exercício** — ver `EXERCICIO.md` | 204 · 404 |

## 5. Estrutura

```
src/main/java/pt/innova/tasks
├── TasksApplication.java         arranque da aplicação
├── common/
│   └── GlobalExceptionHandler    exceções → respostas HTTP (404, 400)
└── task/                         tudo sobre tarefas (package by feature)
    ├── controller/
    │   └── TaskController        HTTP: rotas e status codes
    ├── service/
    │   ├── TaskService           regras de negócio
    │   └── dto/                  o que entra e sai em JSON
    │       ├── CreateTaskRequest
    │       ├── UpdateTaskRequest
    │       └── TaskResponse
    ├── repository/
    │   ├── TaskRepository        acesso à base de dados (Spring Data)
    │   └── entities/
    │       └── Task              entidade JPA ↔ tabela "tasks"
    └── exceptions/
        └── TaskNotFoundException
```

## 6. PostgreSQL sem Docker

### Instalar

- **Windows:** instalador oficial em postgresql.org/download/windows (guarda a password do utilizador `postgres`).
- **macOS:** Postgres.app, ou `brew install postgresql@17 && brew services start postgresql@17`.
- **Linux (Debian/Ubuntu):** `sudo apt install postgresql`.

### Criar o utilizador e as bases de dados

O `db/setup.sql` cria o utilizador `formando` e a base de dados `tarefas`:

```bash
psql -U postgres -f db/setup.sql          # Windows
psql -d postgres -f db/setup.sql          # macOS (Homebrew / Postgres.app)
sudo -u postgres psql < db/setup.sql      # Linux
```

No Windows, se o `psql` não estiver no PATH, abre o **SQL Shell (psql)** do menu Iniciar e escreve
`\i C:/caminho/para/tasks-api/db/setup.sql` (com barras `/`).

Em casa chega a base de dados `tarefas` — os valores por omissão já apontam para ela.

