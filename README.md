# API de Tarefas — Formação de Backend In-Nova

API REST de uma lista de tarefas, em Spring Boot com PostgreSQL. O exercício está no [`EXERCICIO.md`](EXERCICIO.md).

## 1. Pré-requisitos

- **JDK 17 ou superior** (`java -version`). No IntelliJ: *File → Project Structure → SDK → Download JDK*.
- **IntelliJ IDEA** (já traz Maven).
- **Postman**.

## 2. Correr

1. Abre a pasta do projeto no IntelliJ e espera que o Maven descarregue as dependências.
2. *Run → Edit Configurations → TasksApplication → Environment variables*, com o IP que o formador
   escrever no quadro:

   ```
   DB_HOST=<IP do formador>
   ```

3. Corre `TasksApplication` (▶). A API fica em `http://localhost:8080/api/tasks`.

## 3. Testar no Postman

| Método | URL | O que faz | Respostas |
|---|---|---|---|
| `GET` | `http://localhost:8080/api/tasks` | Lista as tarefas | 200 |
| `GET` | `http://localhost:8080/api/tasks/{id}` | Uma tarefa | 200 · 404 |
| `POST` | `http://localhost:8080/api/tasks` | Cria uma tarefa | 201 · 400 |
| `PUT` | `http://localhost:8080/api/tasks/{id}` | Substitui uma tarefa inteira | 200 · 400 · 404 |
| `DELETE` | `http://localhost:8080/api/tasks/{id}` | **Exercício** | 204 · 404 |

No POST e no PUT, escolhe *Body → raw → JSON*:

```json
{ "title": "Preparar formação", "description": "Slides + demo" }
```

O PUT envia a tarefa inteira, incluindo `"done": true` ou `false`.

A base de dados é partilhada por todos: apaga só as tarefas que criaste.

## 4. Estrutura

```
src/main/java/pt/innova/tasks
├── TasksApplication.java         arranque da aplicação
├── common/
│   └── GlobalExceptionHandler    exceções → respostas HTTP (404, 400)
└── task/
    ├── controller/
    │   └── TaskController        HTTP: rotas e status codes
    ├── service/
    │   ├── TaskService           regras de negócio
    │   └── dto/                  o que entra e sai em JSON
    ├── repository/
    │   ├── TaskRepository        acesso à base de dados (Spring Data)
    │   └── entities/
    │       └── Task              entidade JPA ↔ tabela "tasks"
    └── exceptions/
        └── TaskNotFoundException
```
