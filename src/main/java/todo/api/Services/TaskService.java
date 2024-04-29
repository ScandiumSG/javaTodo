package todo.api.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import todo.api.Models.Task;
import todo.api.Repository.TaskRepo;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepo taskRepo;

    /**
     * Retrieve all tasks from the database
     * @return List of Task objects
     */
    public List<Task> getAllTasks() {
        return taskRepo.findAll();
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
        Task createdTask = taskRepo.saveAndFlush(task);

        return createdTask;
    }

    /**
     * Attempt to update the content of a task object
     * @param task The task with updated information
     * @return The updated task, null if no task of provided taskId exists
     */
    public Task updateTask(Task task) {
        Optional<Task> retrievedTask = taskRepo.findById(task.getId());

        if (retrievedTask.isPresent()) {
            Task returnTask = taskRepo.saveAndFlush(task);
            return returnTask;
        } else {
            return null;
        }
    }

    public void deleteTask(Integer id) {
        taskRepo.deleteById(id);
    }

    public void deleteTask(Task task) {
        deleteTask(task.getId());
    }

}
