package global.ostm.backend.doctype;

import global.ostm.backend.core.OstmBus;
import global.ostm.backend.core.OstmCheck;
import global.ostm.backend.core.OstmFieldIsIncorrect;
import global.ostm.backend.core.OstmService;
import global.ostm.backend.doc.OstmDoc;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.AbstractMap;

import static global.ostm.backend.core.OstmBus.Crud.CREATE;
import static global.ostm.backend.core.OstmBus.Crud.UPDATE;

@Service
public class OstmDocTypeService extends OstmService<OstmDocType> {

    protected OstmDocTypeService(OstmBus ostmBus, OstmDocTypeRepository ostmRepository) {
        super(ostmBus, ostmRepository);
    }

    @SuppressWarnings("unused")
    @OstmCheck({CREATE, UPDATE})
    public Mono<AbstractMap.SimpleEntry<String, OstmDoc>> checkDoc(OstmDoc ostmDoc) {
        return ostmRepository.existsById(ostmDoc.getDocType())
                .flatMap(exists -> exists
                        ? Mono.empty()
                        : Mono.error(new OstmFieldIsIncorrect("docType")));
    }

    public Flux<OstmDocType> getDocType() {
        return ostmRepository.findAll(Sort.by("id").ascending());
    }

    public Mono<OstmDocType> getFields() {
        return super.fields(new OstmDocType());
    }

    public Mono<OstmDocType> getDocType(String id) {
        return super.read(id);
    }

    public Mono<OstmDocType> createDocType(OstmDocType ostmDocType) {
        return super.create(ostmDocType);
    }

    public Mono<OstmDocType> updateDocType(String id, OstmDocType ostmDocType) {
        return super.update(id, ostmDocType);
    }
}
