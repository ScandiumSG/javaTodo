package login.api.Models;

import java.time.LocalDateTime;
import java.util.UUID;
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
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;

    @Column(name="email", updatable = true, nullable = true)
    private String email;

    @Column(name="first_name", updatable = true, nullable = true)
    private String firstName;

    @Column(name="last_name", updatable = true, nullable = true)
    private String lastName;

    @Column(name="age", updatable=true, nullable = true)
    private int age;

    @Column(name="admin", updatable = true, nullable = false)
    private boolean admin = false;

    @Column(name="created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;
}
