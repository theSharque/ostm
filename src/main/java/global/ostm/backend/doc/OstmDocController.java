package global.ostm.backend.doc;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doc")
public class OstmDocController {

    private final OstmDocService ostmDocService;

    @GetMapping
    public Flux<OstmDoc> getOstmDoc() {
        return ostmDocService.getDoc();
    }

    @GetMapping("/fields/{key}/{type}")
    public Mono<OstmDoc> getDocFields(@PathVariable String key, @PathVariable String type) {
        return ostmDocService.getFields(key, type);
    }

    @GetMapping("/{id}")
    public Mono<OstmDoc> getDocByKeyAndId(@PathVariable String id) {
        return ostmDocService.getDocById(id);
    }

    @PostMapping
    public Mono<OstmDoc> createDoc(@RequestBody OstmDoc ostmDoc) {
        return ostmDocService.createDoc(ostmDoc);
    }

    @PutMapping("/{id}")
    public Mono<OstmDoc> updateDoc(@PathVariable String id, @RequestBody OstmDoc ostmDoc) {
        return ostmDocService.updateDoc(id, ostmDoc);
    }
}
