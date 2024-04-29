package todo.api.Controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import todo.api.Models.Task;
import todo.api.Services.TaskService;

@RestController
@RequestMapping("/task/v2")
@RequiredArgsConstructor
@Validated
public class ConnectedTaskController {
    private final TaskService taskService;

    @GetMapping("/")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> retrievedTasks = taskService.getAllTasks();
        if (retrievedTasks.size() != 0) {
            return ResponseEntity.ok().body(retrievedTasks);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Integer id) {
        Task retrievedTask = taskService.getTask(id);

        if (retrievedTask != null) {
            return ResponseEntity.ok().body(retrievedTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Task> createTask(@RequestBody Task postTask) {
        System.out.println(postTask);
        Task createdTask = taskService.createTask(postTask);
        if (createdTask != null) {
            return ResponseEntity.ok().body(createdTask);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/")
    public ResponseEntity<Task> updateTask(@RequestBody Task putTask) {
        Task updatedTask = taskService.updateTask(putTask);

        if (updatedTask != null) {
            return ResponseEntity.ok().body(updatedTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Integer id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().body(String.format(String.format("Deleted task with ID %d", id)));
    }
}
