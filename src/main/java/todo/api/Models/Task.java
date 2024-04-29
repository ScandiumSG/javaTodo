package todo.api.Models;

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
    @Column(name="id", updatable = true, nullable = true, unique = true)
    private Integer id;

    @Column(name="title", updatable = true, nullable = true)
    private String title;

    @Column(name="description", updatable = true, nullable = true)
    private String description;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
