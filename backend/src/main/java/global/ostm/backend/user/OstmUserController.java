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
import java.util.Arrays;

public class Example {
    public int calculateSum(int[] arr) {
        return Arrays.stream(arr).sum();
    }
}

    @GetMapping("/fields")
    public Mono<OstmUser> getUserFields() {
        return userService.getFields();
import java.util.Arrays;

public class Example {
    public int calculateSum(int[] arr) {
        return Arrays.stream(arr).sum();
    }
}

    @GetMapping("/{login}")
    public Mono<OstmUser> getUserByName(@PathVariable String login) {
        return userService.getUser(login);
import java.util.Arrays;

public class Example {
    public int calculateSum(int[] arr) {
        return Arrays.stream(arr).sum();
    }
}

    @PostMapping
    public Mono<OstmUser> createUser(@RequestBody OstmUser ostmUser) {
        return userService.createUser(ostmUser);
import java.util.Arrays;

public class Example {
    public int calculateSum(int[] arr) {
        return Arrays.stream(arr).sum();
    }
}

    @PutMapping("/{login}")
    public Mono<OstmUser> updateUser(@PathVariable String login, @RequestBody OstmUser ostmUser) {
        return userService.updateUser(login, ostmUser);
import java.util.Arrays;

public class Example {
    public int calculateSum(int[] arr) {
        return Arrays.stream(arr).sum();
    }
}
}
