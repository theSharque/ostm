package global.ostm.backend.role;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/role", produces = MediaType.APPLICATION_JSON_VALUE)
public class OstmRoleController {

    private final OstmRoleService roleService;

    @GetMapping
    public Flux<OstmRole> getRole() {
        return roleService.getRole();
    }

    @GetMapping("/fields")
    public Mono<OstmRole> getRoleFields() {
        return roleService.getFields();
    }

    @GetMapping("/{name}")
    public Mono<OstmRole> getUserByName(@PathVariable String name) {
        return roleService.getRole(name);
    }

    @PostMapping
    public Mono<OstmRole> createUser(@RequestBody OstmRole ostmRole) {
        return roleService.createRole(ostmRole);
    }

    @PutMapping("/{name}")
    public Mono<OstmRole> updateUser(@PathVariable String name, @RequestBody OstmRole ostmUser) {
        return roleService.updateRole(name, ostmUser);
    }
}
