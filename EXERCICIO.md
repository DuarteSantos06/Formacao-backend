# Exercício — Apagar uma tarefa

**Objetivo:** implementar `DELETE /api/tasks/{id}`. Tempo: ~30 min.

| Situação | Resposta esperada |
|---|---|
| A tarefa existe | `204 No Content` (sem corpo) e a tarefa desaparece da base de dados |
| A tarefa não existe | `404 Not Found` com o erro em JSON |

## Regras

1. A lógica fica no **service**; o **controller** só trata de HTTP.
2. O 404 já está tratado no `GlobalExceptionHandler` — não precisas de `try/catch`.
3. Não é preciso fazer push.

## Passos (procura os `TODO` no código)

1. **`TaskRepository`** — não precisas de escrever nada: que métodos herdas do `JpaRepository`?
2. **`TaskService.deleteTaskById(Long id)`** — se não existir, `TaskNotFoundException`; se existir, apaga.
3. **`TaskController.deleteTaskById`** — que anotação mapeia o método DELETE? Que `ResponseEntity` dá 204?
4. Corre a aplicação e testa no Postman: cria uma tarefa (`POST /api/tasks`) e depois cria o
   pedido `DELETE http://localhost:8080/api/tasks/{id}` com o `id` que te foi devolvido.
   A base de dados é partilhada, por isso apaga só as tuas!
   Repete o mesmo DELETE: o que acontece? O DELETE continua a ser idempotente?

## Pistas (só se ficares preso)

- `existsById(id)` e `deleteById(id)`.
- Cuidado: `deleteById` de um id que não existe **não** lança erro. Como garantes o 404?
- `@DeleteMapping("/{id}")` e `ResponseEntity.noContent().build()`.

