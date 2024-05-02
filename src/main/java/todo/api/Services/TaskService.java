package todo.api.Services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import todo.api.Models.Task;
import todo.api.Repository.TaskRepo;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService {
    private final TaskRepo taskRepo;

    /**
     * Retrieve all tasks from the database
     * @return List of Task objects
     */
    public List<Task> getAllTasks() {
        List<Task> tasks = taskRepo.findAll();
        List<Task> sortedTask = tasks.stream()
            .sorted(Comparator.comparing(Task::getId))
            .collect(Collectors.toList());

        return sortedTask;
    }

    /**
     * Retrieve a specific task from the database
     * @param id - The integer ID of the task to retrieve
     * @return A task if found, otherwise null.
     */
    public Task getTask(Integer id) {
        Optional<Task> retrievedTask = taskRepo.findById(id);
        if (retrievedTask.isPresent()) {
            return retrievedTask.get();
        } else {
            return null;
        }
    }

    /**
     * Attempt to create a new task.
     * @param task The task to add to the database
     * @return The created task
     */
    public Task createTask(Task task) {
        task.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        task.setUpdatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

        Task createdTask = taskRepo.saveAndFlush(task);

        return createdTask;
    }

    /**
     * Attempt to update the content of a task object
     * @param task The task with updated information
     * @return The updated task, null if no task of provided taskId exists
     */
    public Task updateTask(Task task) {
        Optional<Task> findTask = taskRepo.findById(task.getId());
        log.debug("Retrieved object: {}", findTask.get());
        if (findTask.isPresent()) {
            Task retrievedTask = findTask.get();

            retrievedTask.setTitle(task.getTitle());
            retrievedTask.setDescription(task.getDescription());
            retrievedTask.setCompleted(task.isCompleted());
            retrievedTask.setUpdatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

            log.debug("Updated values of: {}", retrievedTask);

            Task returnTask = taskRepo.saveAndFlush(retrievedTask);
            log.debug("Saved values of: {}", returnTask);
            return returnTask;
        } else {
            return null;
        }
    }

    public Task updateTask(int id) {
        Optional<Task> findTask = taskRepo.findById(id);
        return updateTask(findTask.get());
    }

    public void deleteTask(Integer id) {
        taskRepo.deleteById(id);
    }

    public void deleteTask(Task task) {
        deleteTask(task.getId());
    }

}
