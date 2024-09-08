package global.ostm.backend.project;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/project", produces = MediaType.APPLICATION_JSON_VALUE)
public class OstmProjectController {

    private final OstmProjectService ostmProjectService;

    @GetMapping
    public Flux<OstmProject> getProject() {
        return ostmProjectService.getProject();
    }

    @GetMapping("/fields")
    public Mono<OstmProject> getProjectFields() {
        return ostmProjectService.getFields();
    }

    @GetMapping("/{key}")
    public Mono<OstmProject> getProjectByName(@PathVariable String key) {
        return ostmProjectService.getProject(key);
    }

    @PostMapping
    public Mono<OstmProject> createProject(@RequestBody OstmProject ostmProject) {
        return ostmProjectService.createProject(ostmProject);
    }

    @PutMapping("/{key}")
    public Mono<OstmProject> updateProject(@PathVariable String key, @RequestBody OstmProject ostmProject) {
        return ostmProjectService.updateProject(key, ostmProject);
    }
}
