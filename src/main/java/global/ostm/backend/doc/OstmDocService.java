package global.ostm.backend.doc;

import global.ostm.backend.core.*;
import global.ostm.backend.project.OstmProjectRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static global.ostm.backend.core.OstmBus.Crud.CREATE;
import static global.ostm.backend.core.OstmBus.Crud.UPDATE;

@Service
public class OstmDocService extends OstmService<OstmDoc> {

    private final OstmDocRepository docRepository;
    private final OstmProjectRepository ostmProjectRepository;

    protected OstmDocService(OstmBus ostmBus, OstmDocRepository docRepository,
            OstmProjectRepository ostmProjectRepository) {
        super(ostmBus, docRepository);

        this.docRepository = docRepository;
        this.ostmProjectRepository = ostmProjectRepository;
    }

    public Flux<OstmDoc> getDoc() {
        return docRepository.findAll(Sort.by("id").ascending());
    }

    public Mono<OstmDoc> getFields(String key, String docType) {
        OstmDoc ostmDoc = new OstmDoc();
        ostmDoc.setProjectKey(key);
        ostmDoc.setDocType(docType);

        return super.fields(ostmDoc);
    }

    public Mono<OstmDoc> getDocById(String id) {
        return docRepository.findById(id)
                .switchIfEmpty(Mono.error(new OstmObjectNotFound(OstmDoc.class.getSimpleName(), id)))
                .flatMap(super::read);
    }

    public Mono<OstmDoc> createDoc(OstmDoc ostmDoc) {
        if (ostmDoc.getTitle() == null || ostmDoc.getTitle().isEmpty()) {
            return Mono.error(new OstmFieldIsIncorrect("title"));
        }

        return ostmProjectRepository.findAndIncrementNextIdById(ostmDoc.getProjectKey())
                .flatMap(unused -> ostmProjectRepository.findById(ostmDoc.getProjectKey())
                        .map(project -> {
                            ostmDoc.setId(ostmDoc.getProjectKey() + "-" + project.getNextId());
                            return ostmDoc;
                        }).flatMap(doc -> ostmBus.publish(CREATE, ostmDoc)
                                .flatMap(savedDoc -> {
                                    OstmDoc toSave = savedDoc.clone();
                                    toSave.setContent(null);
                                    return docRepository.save(toSave).then(Mono.just(savedDoc));
                                })
                        )
                );
    }

    public Mono<OstmDoc> updateDoc(String id, OstmDoc ostmDoc) {
        if (ostmDoc.getTitle() == null || ostmDoc.getTitle().isEmpty()) {
            return Mono.error(new OstmFieldIsIncorrect("title"));
        }

        return !id.equals(ostmDoc.getId())
                ? Mono.error(new OstmFieldReadOnly("id"))
                : ostmRepository.existsById(id).flatMap(exists -> exists ? ostmBus.publish(UPDATE, ostmDoc)
                        .flatMap(savedDoc -> {
                            OstmDoc toSave = savedDoc.clone();
                            toSave.setContent(null);
                            return docRepository.save(toSave).then(Mono.just(savedDoc));
                        }) : Mono.error(new OstmObjectNotFound(ostmDoc.getClass().getSimpleName(), id))
                );
    }
}
