package login.api.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import login.api.Repository.UserRepo;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @SuppressWarnings("unused")
    @Autowired
    private UserRepo userRepo;

    @Override
    public void run(String... args) throws Exception {
        return;
    }
}