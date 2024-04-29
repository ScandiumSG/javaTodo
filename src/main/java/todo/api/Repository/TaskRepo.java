package todo.api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import todo.api.Models.Task;

public interface TaskRepo extends JpaRepository<Task, Integer>{
    
}
