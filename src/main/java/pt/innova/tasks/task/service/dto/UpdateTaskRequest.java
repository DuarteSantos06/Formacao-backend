package pt.innova.tasks.task.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * What the client sends in a PUT: the whole task (every field).
 */
public record UpdateTaskRequest(
        @NotBlank @Size(max = 100) String title,
        @Size(max = 500) String description,
        @NotNull Boolean done
) {
}
