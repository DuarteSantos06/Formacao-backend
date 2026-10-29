package pt.innova.tasks.task.service;

import org.springframework.stereotype.Service;
import pt.innova.tasks.task.exceptions.TaskNotFoundException;
import pt.innova.tasks.task.repository.TaskRepository;
import pt.innova.tasks.task.repository.entities.Task;
import pt.innova.tasks.task.service.dto.CreateTaskRequest;
import pt.innova.tasks.task.service.dto.TaskResponse;
import pt.innova.tasks.task.service.dto.UpdateTaskRequest;

import java.util.List;

/**
 * Business rules. Knows nothing about HTTP (that's the controller's job)
 * or SQL (that's the repository's job): Single Responsibility.
 */
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    // Constructor injection: Spring hands us the repository (Dependency Inversion)
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(TaskResponse::fromEntity)
                .toList();
    }

    public TaskResponse getTaskById(Long id) {
        return TaskResponse.fromEntity(findTaskByIdOrThrow(id));
    }

    public TaskResponse createTask(CreateTaskRequest request) {
        Task newTask = new Task(request.title(), request.description());
        return TaskResponse.fromEntity(taskRepository.save(newTask));
    }

    public TaskResponse updateTaskById(Long id, UpdateTaskRequest request) {
        Task existingTask = findTaskByIdOrThrow(id);
        existingTask.updateDetails(request.title(), request.description(), request.done());
        return TaskResponse.fromEntity(taskRepository.save(existingTask));
    }

    // TODO EXERCISE: implement deleteTaskById(Long id)
    //  - if the task does not exist -> throw TaskNotFoundException
    //  - if it exists -> delete it

    private Task findTaskByIdOrThrow(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }
}
