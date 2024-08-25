package global.ostm.backend.field;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/field", produces = MediaType.APPLICATION_JSON_VALUE)
public class OstmFieldController {

    private final OstmFieldService ostmFieldService;

    @GetMapping
    public Flux<OstmField> getField() {
        return ostmFieldService.getField();
    }

    @GetMapping("/{view}")
    public Flux<OstmField> getField(@PathVariable String view) {
        return ostmFieldService.getField(view);
    }

    @GetMapping("/{view}/{id}")
    public Mono<OstmField> getFieldByName(@PathVariable String view, @PathVariable String id) {
        return ostmFieldService.getField(view, id);
    }

    @GetMapping("/fields")
    public Mono<OstmField> getFieldFields() {
        return ostmFieldService.getFieldFields();
    }

    @PostMapping
    public Mono<OstmField> createField(@RequestBody OstmField ostmField) {
        return ostmFieldService.createField(ostmField);
    }

    @PutMapping("/{id}")
    public Mono<OstmField> updateField(@PathVariable String id, @RequestBody OstmField ostmField) {
        return ostmFieldService.updateField(id, ostmField);
    }
}
