package login.api.Data;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import login.api.Models.ERole;
import login.api.Models.Role;
import login.api.Models.User;
import login.api.Repository.RoleRepository;
import login.api.Repository.UserRepo;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    PasswordEncoder encoder;

    @Transactional
    @Override
    public void run(String... args) throws Exception {

        seedRoles();
        System.out.println("Seeded roles database");

        seedUsers();

    }

    @Transactional
    public void seedRoles() {
        if (roleRepo.count() == 0) {
            Role roleUser = new Role(ERole.ROLE_USER);
            //Role roleAdmin = new Role(ERole.ROLE_ADMIN);
            //roleRepo.saveAllAndFlush(Arrays.asList(roleUser, roleAdmin));
            roleRepo.saveAllAndFlush(Arrays.asList(roleUser));
        }
    }

    @Transactional
    public void seedUsers() {
        UUID id = UUID.randomUUID(); // Generate a random UUID
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(ERole.ROLE_ADMIN));

        String testPassword = "password";
        // Create a new User object
        User testUser = new User(
            id,
            "testuser",
            "test@test.test",
            encoder.encode(testPassword),
            "t",
            "est",
            42,
            roles,
            LocalDateTime.now() // Set the createdAt property to the current date and time
        );

        User savedUser = userRepo.saveAndFlush(testUser);
        System.out.println(String.format("Seeded user with username: %s, password: %s", savedUser.getUsername(), testPassword));
        
    }
}