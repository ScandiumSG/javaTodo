package todo.api.Models.InputDTO;

import lombok.Data;

@Data
public class PostItem {
    private String title;

    private String description;

    public PostItem(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
