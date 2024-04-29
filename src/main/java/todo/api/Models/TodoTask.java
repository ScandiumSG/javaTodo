package todo.api.Models;

public class TodoTask {
    private String title;
    private String description;

    public TodoTask() {}

    public TodoTask(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }
}
