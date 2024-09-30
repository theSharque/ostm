package global.ostm.backend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class OstmUserController {

    private final OstmUserService userService;

    @GetMapping
    public Flux<OstmUser> getUser() {
        return userService.getUser();
    }

    @GetMapping("/fields")
    public Mono<OstmUser> getUserFields() {
        return userService.getFields();
    }

    @GetMapping("/{login}")
    public Mono<OstmUser> getUserByName(@PathVariable String login) {
        return userService.getUser(login);
    }

    @PostMapping
    public Mono<OstmUser> createUser(@RequestBody OstmUser ostmUser) {
        return userService.createUser(ostmUser);
    }

    @PutMapping("/{login}")
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OstmUserController {
    private final Logger logger = Logger.getLogger(OstmUserController.class.getName());
    private final UserService userService;

    public OstmUserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/users/{login}")
    public Mono<OstmUser> updateUser(@PathVariable String login, @RequestBody OstmUser ostmUser) {
        logger.log(Level.INFO, "Updating user with login: {0}", login);

        // Validate the input
        if (ostmUser == null || login == null || login.isEmpty()) {
            return Mono.error(new IllegalArgumentException("Invalid user data or login"));
        }

        // Update the user in the service layer
        return userService.updateUser(login, ostmUser)
                .doOnSuccess(updatedUser -> logger.log(Level.INFO, "User updated successfully: {0}", updatedUser))
                .onErrorResume(e -> {
                    logger.log(Level.SEVERE, "Failed to update user", e);
                    return Mono.error(new RuntimeException("Failed to update user"));
                });
    }
}

class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<OstmUser> updateUser(String login, OstmUser ostmUser) {
        return userRepository.findByLogin(login)
                .flatMap(existingUser -> {
                    // Update the existing user with the new data
                    if (ostmUser.getName() != null) existingUser.setName(ostmUser.getName());
                    if (ostmUser.getEmail() != null) existingUser.setEmail(ostmUser.getEmail());
                    return userRepository.save(existingUser);
                })
                .switchIfEmpty(Mono.error(new IllegalArgumentException("User not found")));
    }
}

interface UserRepository {
    Mono<OstmUser> findByLogin(String login);
    Mono<OstmUser> save(OstmUser user);
}

class OstmUser {
    private String name;
    private String email;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
        return userService.updateUser(login, ostmUser);
    }
}
