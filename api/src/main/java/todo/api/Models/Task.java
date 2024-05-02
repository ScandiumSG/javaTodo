package todo.api.Models;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", updatable = false, nullable = true, unique = true)
    private Integer id;

    @Column(name="title", updatable = true, nullable = true)
    private String title;

    @Column(name="description", updatable = true, nullable = true)
    private String description;

    @Column(name="task_complete", updatable = true, nullable = false)
    private boolean completed;
    
    @Column(name="created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Column(name="updated_at", updatable = true, nullable = false)
    private LocalDateTime updatedAt;

    public Task(String title, String description, boolean complete) {
        this.title = title;
        this.description = description;
        this.completed = complete;
    }

    public Task(String title, String description) {
        this(title, description, false);
    }
}
