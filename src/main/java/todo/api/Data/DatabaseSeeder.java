package todo.api.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import todo.api.Models.Task;
import todo.api.Repository.TaskRepo;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private TaskRepo taskRepo;

    @Override
    public void run(String... args) throws Exception {
        // Insert seed data
        Task task1 = new Task(1, "Hygiene", "Take a shower and brush your teeth");
        Task task2 = new Task(2, "Chores", "Load and start the dishwasher");

        taskRepo.save(task1);
        taskRepo.save(task2);

        // Add more seed data as needed
        System.out.println("Seed data added");
    }
}