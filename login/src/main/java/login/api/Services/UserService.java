package login.api.Services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import login.api.Models.User;
import login.api.Repository.UserRepo;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepo userRepo;

    /**
     * Retrieve all users from the database
     * @return List of User objects
     */
    public List<User> getAllUsers() {
        List<User> users = userRepo.findAll();
        List<User> sortedUsers = users.stream()
            .sorted(Comparator.comparing(User::getId))
            .collect(Collectors.toList());

        return sortedUsers;
    }

    /**
     * Retrieve a specific user from the database
     * @param id - The integer ID of the user to retrieve
     * @return A user if found, otherwise null.
     */
    public User getUser(UUID id) {
        Optional<User> retrievedUser = userRepo.findById(id);
        if (retrievedUser.isPresent()) {
            return retrievedUser.get();
        } else {
            return null;
        }
    }

    /**
     * Attempt to create a new user.
     * @param task The user to add to the database
     * @return The created user
     */
    public User createUser(User user) {
        user.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

        User createdUser = userRepo.saveAndFlush(user);

        return createdUser;
    }

    /**
     * Attempt to update the content of a user object
     * @param user The user with updated information
     * @return The updated user, null if no user of provided userId exists
     */
    public User updateUser(User user) {
        Optional<User> findUser = userRepo.findById(user.getId());
        log.debug("Retrieved object: {}", findUser.get());
        if (findUser.isPresent()) {
            User retrievedUser = findUser.get();

            log.debug("Updated values of: {}", retrievedUser);

            User returnUser = userRepo.saveAndFlush(retrievedUser);
            log.debug("Saved values of: {}", returnUser);
            return returnUser;
        } else {
            return null;
        }
    }


    public void deleteUser(UUID id) {
        userRepo.deleteById(id);
    }

    public void deleteUser(User user) {
        deleteUser(user.getId());
    }

}
