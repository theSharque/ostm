package global.ostm.backend.doctype;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/docType")
public class OstmDocTypeController {

    private final OstmDocTypeService ostmDocTypeService;

    @GetMapping
    public Flux<OstmDocType> getDocType() {
        return ostmDocTypeService.getDocType();
    }

    @GetMapping("/fields")
    public Mono<OstmDocType> getDocTypeFields() {
        return ostmDocTypeService.getFields();
    }

    @GetMapping("/{id}")
    public Mono<OstmDocType> getDocTypeByName(@PathVariable String id) {
        return ostmDocTypeService.getDocType(id);
    }

    @PostMapping
    public Mono<OstmDocType> createDocType(@RequestBody OstmDocType ostmDocType) {
        return ostmDocTypeService.createDocType(ostmDocType);
    }

    @PutMapping("/{id}")
    public Mono<OstmDocType> updateDocType(@PathVariable String id, @RequestBody OstmDocType ostmDocType) {
        return ostmDocTypeService.updateDocType(id, ostmDocType);
    }
}
