package login.api.Models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.EnumType;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;

    @NotBlank
    @Column(name="username", updatable=false, nullable= false, unique = true)
    private String username;

    @Email
    @NotBlank
    @Column(name="email", updatable = true, nullable = true)
    private String email;

    @NotBlank
    private String password;

    @Column(name="first_name", updatable = true, nullable = true)
    private String firstName;

    @Column(name="last_name", updatable = true, nullable = true)
    private String lastName;

    @Column(name="age", updatable=true, nullable = true)
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(name="role", updatable = true, nullable = false)
    private ERole role;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Role> roles = new HashSet<>();

    @Column(name="created_at", updatable = false, nullable = true)
    private LocalDateTime createdAt;
}
