package todo.api.Controllers;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import todo.api.Models.DTOs.TaskItem;
import todo.api.Models.DTOs.TaskList;
import todo.api.Models.DTOs.TodoTask;

@RestController
@RequestMapping("/task/v1")
public class TaskController {
    
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    private static final String titleTemplate = "I did a thing";
    private static final String descriptionTemplate = "I did the thing by first doing a thing then another thing.";
    private int counter = 0;
    private TaskItem task1 = new TaskItem(counter++, titleTemplate, descriptionTemplate);
    private TaskItem task2 = new TaskItem(counter++, titleTemplate, descriptionTemplate);

    private ArrayList<TaskItem> allTasks = new ArrayList<TaskItem>();

    public TaskController() {
        this.allTasks.add(task1);
        this.allTasks.add(task2);
    }


    @GetMapping("/{id}")
    public ResponseEntity<TaskItem> taskItem(@PathVariable("id") int id) {
        for (TaskItem item : this.allTasks) {
            if (item.id() == id) {
                logger.info("Sent TaskItem: {}", item);
                return new ResponseEntity<TaskItem>(item, HttpStatus.OK);
            }
        }
        return new ResponseEntity<TaskItem>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<TaskList> taskList() {
        TaskList tasks = new TaskList(new ArrayList<TaskItem>(this.allTasks));
        logger.info("Sent TaskList: {}", tasks);
        return new ResponseEntity<TaskList>(tasks, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<TaskItem> createTask(@RequestBody TodoTask newTask) {
        try {
            TaskItem newTaskItem = new TaskItem(counter++, newTask.getTitle(), newTask.getDescription());
            this.allTasks.add(newTaskItem);
            logger.info("Created Task: {}", newTask);
            return new ResponseEntity<TaskItem>(newTaskItem, HttpStatus.OK);
        } catch (Error e) {
            logger.error("Error creating task: {}", e.getMessage());
            return new ResponseEntity<TaskItem>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskItem> updateTask(@RequestBody TodoTask updatedTask, @PathVariable("id") Integer id) {
        try {
            TaskItem updateTask = null;
            for (TaskItem task: allTasks) {
                if (task.id() == id) {
                    updateTask = task;
                    break;
                }
            }

            if (updateTask == null) {
                logger.error("Attempted to update task with id {}, none found", id);
                return new ResponseEntity<TaskItem>(HttpStatus.NOT_FOUND);
            }

            // Make new TaskItem record 
            TaskItem recordToUpdate = new TaskItem(id, updatedTask.getTitle(), updatedTask.getDescription());

            // Insert the new TaskItem record
            allTasks.set(allTasks.indexOf(updateTask), recordToUpdate);

            logger.error("Updated task: {}", recordToUpdate);

            return new ResponseEntity<TaskItem>(recordToUpdate, HttpStatus.CREATED);

        } catch (Error e) {
            logger.error("Error updating task: {}", e.getMessage());
            throw new IllegalArgumentException("Invalid task properties provided");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskItem> deleteTask(@PathVariable("id") Integer id) {
        try {
            logger.info("Deleting task with id: {}", id);

            TaskItem itemToDelete = null;
            for (TaskItem task: allTasks) {
                if (task.id() == id) {
                    itemToDelete = task;
                    break;
                }
            }

            if (itemToDelete == null) {
                logger.error("Could not find item with id: {}", id);
                return new ResponseEntity<TaskItem>(HttpStatus.NOT_FOUND);
            }

            boolean deletedTask = this.allTasks.remove(itemToDelete);
            if (deletedTask) {
                return new ResponseEntity<TaskItem>(itemToDelete, HttpStatus.OK);
            } else {
                return new ResponseEntity<TaskItem>(HttpStatus.BAD_REQUEST);
            }

        } catch (Error e) {
            logger.error("Error deleting task: {}", e.getMessage());
            return new ResponseEntity<TaskItem>(HttpStatus.BAD_REQUEST);
        }
    }
}
