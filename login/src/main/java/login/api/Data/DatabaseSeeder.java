package login.api.Data;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import login.api.Models.ERole;
import login.api.Models.Role;
import login.api.Models.User;
import login.api.Repository.UserRepo;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private UserRepo userRepo;

    @Override
    public void run(String... args) throws Exception {
        UUID id = new UUID(5, 10);
        User testUser = new User(id, 
            "testuser", 
            "test@test.test", 
            "password", 
            "t",
            "est", 
            42, 
            ERole.ROLE_ADMIN,
            null, 
            null
         );

        User savedUser = userRepo.save(testUser);
        System.out.println(String.format("Seeded user with username: %s, password: %s", savedUser.getUsername(), savedUser.getPassword()));
    }
}