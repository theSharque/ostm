package global.ostm.backend.flow;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/flow")
@RequiredArgsConstructor
public class OstmDocFlowController {

    private final OstmDocFlowService ostmDocFlowService;

    @GetMapping
    public Flux<OstmDocFlow> getDocFlow() {
        return ostmDocFlowService.getDocFlow();
    }

    @GetMapping("/fields")
    public Mono<OstmDocFlow> getDocFlowFields() {
        return ostmDocFlowService.getFields();
    }

    @GetMapping("/{id}")
    public Mono<OstmDocFlow> getDocFlowByName(@PathVariable String id) {
        return ostmDocFlowService.getDocFlow(id);
    }

    @PostMapping
    public Mono<OstmDocFlow> createDocFlow(@RequestBody OstmDocFlow ostmDocFlow) {
        return ostmDocFlowService.createDocFlow(ostmDocFlow);
    }

    @PutMapping("/{id}")
    public Mono<OstmDocFlow> updateDocFlow(@PathVariable String id, @RequestBody OstmDocFlow ostmDocFlow) {
        return ostmDocFlowService.updateDocFlow(id, ostmDocFlow);
    }
}
