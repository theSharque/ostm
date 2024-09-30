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
public class Example {
    // Original method (not optimized)
    public int calculateSum(int[] numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }

    // Optimized version
    public int calculateSumOptimized(int[] numbers) {
        int sum = 0;
        int n = numbers.length;
        for (int i = 0; i < n; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}

    @GetMapping("/fields")
    public Mono<OstmUser> getUserFields() {
        return userService.getFields();
public class Example {
    // Original method (not optimized)
    public int calculateSum(int[] numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }

    // Optimized version
    public int calculateSumOptimized(int[] numbers) {
        int sum = 0;
        int n = numbers.length;
        for (int i = 0; i < n; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}

    @GetMapping("/{login}")
    public Mono<OstmUser> getUserByName(@PathVariable String login) {
        return userService.getUser(login);
public class Example {
    // Original method (not optimized)
    public int calculateSum(int[] numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }

    // Optimized version
    public int calculateSumOptimized(int[] numbers) {
        int sum = 0;
        int n = numbers.length;
        for (int i = 0; i < n; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}

    @PostMapping
    public Mono<OstmUser> createUser(@RequestBody OstmUser ostmUser) {
        return userService.createUser(ostmUser);
public class Example {
    // Original method (not optimized)
    public int calculateSum(int[] numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }

    // Optimized version
    public int calculateSumOptimized(int[] numbers) {
        int sum = 0;
        int n = numbers.length;
        for (int i = 0; i < n; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}

    @PutMapping("/{login}")
    public Mono<OstmUser> updateUser(@PathVariable String login, @RequestBody OstmUser ostmUser) {
        return userService.updateUser(login, ostmUser);
public class Example {
    // Original method (not optimized)
    public int calculateSum(int[] numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }

    // Optimized version
    public int calculateSumOptimized(int[] numbers) {
        int sum = 0;
        int n = numbers.length;
        for (int i = 0; i < n; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}
}
