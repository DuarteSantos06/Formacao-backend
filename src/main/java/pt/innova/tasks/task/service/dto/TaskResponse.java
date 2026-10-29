package pt.innova.tasks.task.service.dto;

import pt.innova.tasks.task.repository.entities.Task;

import java.time.Instant;

/**
 * What the API returns. We never expose the entity directly:
 * that way we can change the database without breaking the clients.
 */
public record TaskResponse(
        Long id,
        String title,
        String description,
        boolean done,
        Instant createdAt
) {

    public static TaskResponse fromEntity(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.isDone(),
                task.getCreatedAt()
        );
    }
}
