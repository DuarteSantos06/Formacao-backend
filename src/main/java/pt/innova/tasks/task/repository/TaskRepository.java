package pt.innova.tasks.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.innova.tasks.task.repository.entities.Task;

import java.util.List;

/**
 * Just an interface: Spring Data generates the implementation at runtime.
 * We already inherit save, findById, findAll, existsById, deleteById, count...
 */
public interface TaskRepository extends JpaRepository<Task, Long> {

    // Query derived from the method name -> SELECT ... FROM tasks ORDER BY created_at DESC
    List<Task> findAllByOrderByCreatedAtDesc();
}
