package pt.innova.tasks.task.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pt.innova.tasks.task.service.TaskService;
import pt.innova.tasks.task.service.dto.CreateTaskRequest;
import pt.innova.tasks.task.service.dto.TaskResponse;
import pt.innova.tasks.task.service.dto.UpdateTaskRequest;

import java.util.List;

/**
 * HTTP layer: receives requests, calls the service and returns
 * the right response (status code + JSON). No business rules here.
 */
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // GET /api/tasks -> 200 OK
    @GetMapping
    public List<TaskResponse> getAllTasks() {
        return taskService.getAllTasks();
    }

    // GET /api/tasks/{id} -> 200 OK | 404 Not Found
    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    // POST /api/tasks -> 201 Created | 400 Bad Request
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse createTask(@Valid @RequestBody CreateTaskRequest request) {
        return taskService.createTask(request);
    }

    // PUT /api/tasks/{id} -> 200 OK | 400 Bad Request | 404 Not Found
    @PutMapping("/{id}")
    public TaskResponse updateTaskById(@PathVariable Long id, @Valid @RequestBody UpdateTaskRequest request) {
        return taskService.updateTaskById(id, request);
    }

    // TODO EXERCISE: deleteTaskById -> DELETE /api/tasks/{id} -> 204 No Content | 404 Not Found
}
