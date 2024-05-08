package todo.api.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

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
        List<Task> tasks = new ArrayList<Task>(); 

        tasks.add(new Task(
                1, 
                "Hygiene", 
                "Take a shower and brush your teeth", 
                true, 
                LocalDateTime.of(LocalDate.of(2023, 3, 20), LocalTime.now().truncatedTo(ChronoUnit.SECONDS)), 
                LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)
            )
        );

        tasks.add(new Task(
                2, 
                "Chores", 
                "Load and start the dishwasher", 
                false, 
                LocalDateTime.of(LocalDate.of(2023, 3, 22), LocalTime.now().truncatedTo(ChronoUnit.SECONDS)), 
                LocalDateTime.of(LocalDate.of(2023, 3, 22), LocalTime.now()).truncatedTo(ChronoUnit.SECONDS)
            )
        );

        tasks.add(new Task(
                3, 
                "Pay bills", 
                "Approve invoices and transfer money to food account", 
                false, 
                LocalDateTime.of(LocalDate.of(2023, 4, 15), LocalTime.now().truncatedTo(ChronoUnit.SECONDS)), 
                LocalDateTime.of(LocalDate.of(2023, 4, 15), LocalTime.now()).truncatedTo(ChronoUnit.SECONDS)
            )
        );

        tasks.add(new Task(
                4,
                "Paint wall",
                "Paint the south wall green, and the west wall yellow",
                false,
                LocalDateTime.of(LocalDate.of(2023, 6, 20), LocalTime.now().truncatedTo(ChronoUnit.SECONDS)), 
                LocalDateTime.of(LocalDate.of(2023, 6, 25), LocalTime.now()).truncatedTo(ChronoUnit.SECONDS)
            )
        );

        tasks.add(new Task(
            5,
            "Take out trash",
            "Empty the trash bin",
            false,
            LocalDateTime.of(LocalDate.of(2024, 2, 13), LocalTime.now().truncatedTo(ChronoUnit.SECONDS)), 
            LocalDateTime.of(LocalDate.of(2024, 2, 13), LocalTime.now()).truncatedTo(ChronoUnit.SECONDS)
        )
    );

        taskRepo.saveAll(tasks);

        // Add more seed data as needed
        System.out.println(
            String.format("%s %s [seeding] Seed data added", LocalDate.now().toString(), LocalTime.now().truncatedTo(ChronoUnit.SECONDS).toString())
        );
    }
}