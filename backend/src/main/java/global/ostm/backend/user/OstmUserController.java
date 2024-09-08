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
    public Mono<OstmUser> updateUser(@PathVariable String login, @RequestBody OstmUser ostmUser) {
        return userService.updateUser(login, ostmUser);
    }
}
