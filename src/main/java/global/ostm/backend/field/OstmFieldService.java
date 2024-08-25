package global.ostm.backend.field;

import global.ostm.backend.core.*;
import global.ostm.backend.doc.OstmDoc;
import global.ostm.backend.project.OstmProject;
import global.ostm.backend.role.OstmRole;
import global.ostm.backend.user.OstmUser;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.AbstractMap;

import static global.ostm.backend.core.OstmBus.Crud.*;

@Service
public class OstmFieldService extends OstmService<OstmField> {

    private final OstmFieldRepository ostmFieldRepository;

    @SuppressWarnings("unused")
    @OstmCheck({FIELDS})
    public Mono<AbstractMap.SimpleEntry<String, OstmProject>> addProject(OstmProject ostmProject) {
        return ostmFieldRepository.findAllByView("PROJECT")
                .collectMap(OstmModel::getId, ostmField -> ostmField)
                .map(stringOstmFieldMap -> {
                    OstmProject project = new OstmProject();
                    project.setContent(stringOstmFieldMap);

                    return new AbstractMap.SimpleEntry<>("fields", project);
                });
    }

    @SuppressWarnings("unused")
    @OstmCheck({FIELDS})
    public Mono<AbstractMap.SimpleEntry<String, OstmUser>> addUser(OstmUser ostmUser) {
        return ostmFieldRepository.findAllByView("USER")
                .collectMap(OstmModel::getId, ostmField -> ostmField)
                .map(stringOstmFieldMap -> {
                    OstmUser user = new OstmUser();
                    user.setContent(stringOstmFieldMap);

                    return new AbstractMap.SimpleEntry<>("fields", user);
                });
    }

    @SuppressWarnings("unused")
    @OstmCheck({FIELDS})
    public Mono<AbstractMap.SimpleEntry<String, OstmRole>> addRole(OstmRole ostmRole) {
        return ostmFieldRepository.findAllByView("ROLE")
                .collectMap(OstmModel::getId, ostmField -> ostmField)
                .map(stringOstmFieldMap -> {
                    OstmRole role = new OstmRole();
                    role.setContent(stringOstmFieldMap);

                    return new AbstractMap.SimpleEntry<>("fields", role);
                });
    }

    @SuppressWarnings("unused")
    @OstmCheck({FIELDS})
    public Mono<AbstractMap.SimpleEntry<String, OstmDoc>> addDoc(OstmDoc ostmDoc) {
        return ostmFieldRepository.findAllByProjectKeyAndView(ostmDoc.getProjectKey(), ostmDoc.getDocType())
                .collectMap(OstmModel::getId, ostmField -> ostmField)
                .map(stringOstmFieldMap -> {
                    OstmDoc role = new OstmDoc();
                    role.setContent(stringOstmFieldMap);

                    return new AbstractMap.SimpleEntry<>("fields", role);
                });
    }

    @SuppressWarnings("unused")
    @OstmCheck({CREATE, UPDATE})
    public Mono<AbstractMap.SimpleEntry<String, OstmDoc>> checkDoc(OstmDoc ostmDoc) {
        return ostmDoc.getContent() == null ? Mono.empty()
                : ostmFieldRepository.findAllById(ostmDoc.getContent().keySet())
                        .count()
                        .flatMap(cnt -> cnt == ostmDoc.getContent().keySet().size() ? Mono.empty()
                                : Mono.error(new OstmFieldIsIncorrect("content")));
    }

    protected OstmFieldService(OstmBus ostmBus, OstmFieldRepository ostmFieldRepository) {
        super(ostmBus, ostmFieldRepository);
        this.ostmFieldRepository = ostmFieldRepository;
    }

    public Flux<OstmField> getField() {
        return ostmFieldRepository.findAll(Sort.by("view", "id").ascending());
    }

    public Flux<OstmField> getField(String viewType) {
        return ostmFieldRepository.findAllByView(viewType);
    }

    public Mono<OstmField> getField(String view, String id) {
        return ostmFieldRepository.findByViewAndId(view, id);
    }

    public Mono<OstmField> getFieldFields() {
        return super.fields(new OstmField());
    }

    public Mono<OstmField> createField(OstmField ostmField) {
        ostmField.setId(ostmField.getId());
        return super.create(ostmField);
    }

    public Mono<OstmField> updateField(String id, OstmField ostmField) {
        ostmField.setId(ostmField.getId());
        return super.update(id, ostmField);
    }
}
