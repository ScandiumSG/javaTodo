package todo.api;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import todo.api.Models.TodoTask;
import todo.api.Models.TaskItem;
import todo.api.Models.TaskList;

@RestController
public class TaskController {
    
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    private static final String titleTemplate = "I did a thing";
    private static final String descriptionTemplate = "I did the thing by first doing a thing then another thing.";
    private int counter = 0;
    private TaskItem noTask = new TaskItem(-1, "Invalid", "No task of provided ID could be found.");
    private TaskItem task1 = new TaskItem(counter++, titleTemplate, descriptionTemplate);
    private TaskItem task2 = new TaskItem(counter++, titleTemplate, descriptionTemplate);

    private ArrayList<TaskItem> allTasks = new ArrayList<TaskItem>();

    public TaskController() {
        this.allTasks.add(task1);
        this.allTasks.add(task2);
    }


    @GetMapping("/task/{id}")
    public TaskItem taskItem(@PathVariable("id") int id) {
        for (TaskItem item : this.allTasks) {
            if (item.id() == id) {
                logger.info("Sent TaskItem: {}", item);
                return item;
            }
        }
        return noTask;
    }

    @GetMapping("/tasks")
    public TaskList taskList() {
        logger.info("Sent TaskList: {}", this.allTasks);
        return new TaskList(this.allTasks);
    }

    @PostMapping("/task")
    public TaskItem createTask(@RequestBody TodoTask newTask) {
        try {
            logger.info("Received TodoTask: {}", newTask);

            TaskItem newTaskItem = new TaskItem(counter++, newTask.getTitle(), newTask.getDescription());
            this.allTasks.add(newTaskItem);
            return newTaskItem;
        } catch (Error e) {
            logger.error("Error creating task: {}", e.getMessage());
            throw new IllegalArgumentException("Invalid task fields provided");
        }
    }
}
