package login.api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import login.api.Models.User;

public interface UserRepo extends JpaRepository<User, UUID>{
    
}
