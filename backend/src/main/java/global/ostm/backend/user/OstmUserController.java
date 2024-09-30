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
public int sumArray(int[] array) {
    return Arrays.stream(array).sum();
}

    @GetMapping("/fields")
    public Mono<OstmUser> getUserFields() {
        return userService.getFields();
public int sumArray(int[] array) {
    return Arrays.stream(array).sum();
}

    @GetMapping("/{login}")
    public Mono<OstmUser> getUserByName(@PathVariable String login) {
        return userService.getUser(login);
public int sumArray(int[] array) {
    return Arrays.stream(array).sum();
}

    @PostMapping
    public Mono<OstmUser> createUser(@RequestBody OstmUser ostmUser) {
        return userService.createUser(ostmUser);
public int sumArray(int[] array) {
    return Arrays.stream(array).sum();
}

    @PutMapping("/{login}")
    public Mono<OstmUser> updateUser(@PathVariable String login, @RequestBody OstmUser ostmUser) {
        return userService.updateUser(login, ostmUser);
public int sumArray(int[] array) {
    return Arrays.stream(array).sum();
}
}
