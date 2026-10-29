package pt.innova.tasks.task.repository.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

/**
 * JPA entity: each Task object maps to one row of the "tasks" table.
 * There are no public setters — state only changes through meaningful methods.
 */
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private boolean done;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    protected Task() {
        // Empty constructor required by JPA
    }

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.done = false;
        this.createdAt = Instant.now();
    }

    public void updateDetails(String title, String description, boolean done) {
        this.title = title;
        this.description = description;
        this.done = done;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
