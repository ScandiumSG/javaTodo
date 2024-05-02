package todo.api.Data;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;

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
        Task task1 = new Task(
            1, 
            "Hygiene", 
            "Take a shower and brush your teeth", 
            true, 
            LocalDateTime.of(LocalDate.of(2023, 3, 20), LocalTime.now().truncatedTo(ChronoUnit.SECONDS)), 
            LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        Task task2 = new Task(
            2, 
            "Chores", 
            "Load and start the dishwasher", 
            false, 
            LocalDateTime.of(LocalDate.of(2023, 3, 22), LocalTime.now().truncatedTo(ChronoUnit.SECONDS)), 
            LocalDateTime.of(LocalDate.of(2023, 3, 22), LocalTime.now()).truncatedTo(ChronoUnit.SECONDS));
        Task task3 = new Task(
            3, 
            "Pay bills", 
            "Approve invoices and transfer money to food account", 
            false, 
            LocalDateTime.of(LocalDate.of(2023, 4, 15), LocalTime.now().truncatedTo(ChronoUnit.SECONDS)), 
            LocalDateTime.of(LocalDate.of(2023, 4, 15), LocalTime.now()).truncatedTo(ChronoUnit.SECONDS));

        taskRepo.save(task1);
        taskRepo.save(task2);
        taskRepo.save(task3);

        // Add more seed data as needed
        System.out.println(
            String.format("%s %s [seeding] Seed data added", LocalDate.now().toString(), LocalTime.now().truncatedTo(ChronoUnit.SECONDS).toString())
        );
    }
}