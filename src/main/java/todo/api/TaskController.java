package todo.api;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import todo.api.Models.TaskItem;
import todo.api.Models.TaskList;

@RestController
public class TaskController {
    
    private static final String titleTemplate = "I did a thing";
    private static final String descriptionTemplate = "I did the thing by first doing a thing then another thing.";
    private int counter = 0;
    private TaskItem noTask = new TaskItem(-1, "Invalid", "No task of provided ID could be found.");
    private TaskItem task1 = new TaskItem(counter++, titleTemplate, descriptionTemplate);
    private TaskItem task2 = new TaskItem(counter++, titleTemplate, descriptionTemplate);

    private ArrayList<TaskItem> allTasks = new ArrayList<TaskItem>();

    public TaskController() {
        this.allTasks.add(noTask);
        this.allTasks.add(task1);
        this.allTasks.add(task2);
    }


    @GetMapping("/task")
    public TaskItem taskItem(@RequestParam(value= "id", defaultValue = "0") int value) {
        for (TaskItem item : this.allTasks) {
            if (item.id() == value) {
                return item;
            }
        }
        return noTask;
    }

    @GetMapping("/tasks")
    public TaskList taskList() {
        return new TaskList(this.allTasks);
    }
}
