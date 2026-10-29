package pt.innova.tasks.task.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * What the client sends in a POST. A record is immutable and already comes with
 * a constructor, getters, equals, hashCode and toString.
 */
public record CreateTaskRequest(
        @NotBlank @Size(max = 100) String title,
        @Size(max = 500) String description
) {
}
