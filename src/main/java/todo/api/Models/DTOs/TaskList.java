package todo.api.Models.DTOs;

import java.util.ArrayList;

public record TaskList (ArrayList<TaskItem> items) {}
