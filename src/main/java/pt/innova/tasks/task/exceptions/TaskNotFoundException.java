package pt.innova.tasks.task.exceptions;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(Long id) {
        super("Task " + id + " not found");
    }
}
