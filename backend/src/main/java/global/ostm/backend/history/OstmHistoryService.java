package global.ostm.backend.history;

import global.ostm.backend.content.OstmContent;
import global.ostm.backend.content.OstmContentRepository;
import global.ostm.backend.core.OstmAuthUtils;
import global.ostm.backend.core.OstmBus;
import global.ostm.backend.core.OstmCheck;
import global.ostm.backend.core.OstmModel;
import global.ostm.backend.doc.OstmDoc;
import global.ostm.backend.doc.OstmDocRepository;
import global.ostm.backend.doctype.OstmDocType;
import global.ostm.backend.doctype.OstmDocTypeRepository;
import global.ostm.backend.field.OstmField;
import global.ostm.backend.field.OstmFieldRepository;
import global.ostm.backend.flow.OstmDocFlow;
import global.ostm.backend.flow.OstmDocFlowRepository;
import global.ostm.backend.project.OstmProject;
import global.ostm.backend.project.OstmProjectRepository;
import global.ostm.backend.role.OstmRole;
import global.ostm.backend.role.OstmRoleRepository;
import global.ostm.backend.user.OstmUser;
import global.ostm.backend.user.OstmUserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.AbstractMap;
import java.util.Arrays;

import static global.ostm.backend.core.OstmBus.Crud.CREATE;
import static global.ostm.backend.core.OstmBus.Crud.UPDATE;

@Service
public class OstmHistoryService {

    private final OstmBus ostmBus;
    private final OstmHistoryRepository ostmHistoryRepository;
    private final OstmProjectRepository ostmProjectRepository;
    private final OstmUserRepository ostmUserRepository;
    private final OstmRoleRepository ostmRoleRepository;
    private final OstmDocTypeRepository ostmDocTypeRepository;
    private final OstmDocFlowRepository ostmDocFlowRepository;
    private final OstmFieldRepository ostmFieldRepository;
    private final OstmContentRepository ostmContentRepository;
    private final OstmDocRepository ostmDocRepository;

    protected OstmHistoryService(OstmBus ostmBus, OstmHistoryRepository ostmHistoryRepository,
            OstmProjectRepository ostmProjectRepository, OstmUserRepository ostmUserRepository,
            OstmRoleRepository ostmRoleRepository, OstmDocTypeRepository ostmDocTypeRepository,
            OstmDocFlowRepository ostmDocFlowRepository, OstmFieldRepository ostmFieldRepository,
            OstmContentRepository ostmContentRepository, OstmDocRepository ostmDocRepository) {
        this.ostmBus = ostmBus;
        this.ostmHistoryRepository = ostmHistoryRepository;
        this.ostmProjectRepository = ostmProjectRepository;
        this.ostmUserRepository = ostmUserRepository;
        this.ostmRoleRepository = ostmRoleRepository;

        installBus();
        this.ostmDocTypeRepository = ostmDocTypeRepository;
        this.ostmDocFlowRepository = ostmDocFlowRepository;
        this.ostmFieldRepository = ostmFieldRepository;
        this.ostmContentRepository = ostmContentRepository;
        this.ostmDocRepository = ostmDocRepository;
    }

    protected final void installBus() {
        Arrays.stream(this.getClass().getDeclaredMethods())
                .filter(method -> method.getAnnotation(OstmCheck.class) != null)
                .forEach(method -> Arrays.stream(method.getAnnotation(OstmCheck.class).value())
                        .forEach(crud -> ostmBus.subscribe(crud, this, method)));
    }

    private <T extends OstmModel> Mono<AbstractMap.SimpleEntry<String, T>> saveCreateHistory(T ostmModel) {
        return ostmHistoryRepository.save(
                OstmHistory.builder()
                        .objectId(ostmModel.getId())
                        .objectType(ostmModel.getClass().getSimpleName())
                        .userId(OstmAuthUtils.getCurrentUser())
                        .newValue(ostmModel)
                        .build()
        ).then(Mono.empty());
    }

    @SuppressWarnings("unused")
    @OstmCheck({CREATE})
    public Mono<AbstractMap.SimpleEntry<String, OstmModel>> createProject(OstmProject ostmProject) {
        return saveCreateHistory(ostmProject);
    }

    @SuppressWarnings("unused")
    @OstmCheck({CREATE})
    public Mono<AbstractMap.SimpleEntry<String, OstmModel>> createUser(OstmUser ostmUser) {
        return saveCreateHistory(ostmUser);
    }

    @SuppressWarnings("unused")
    @OstmCheck({CREATE})
    public Mono<AbstractMap.SimpleEntry<String, OstmModel>> createRole(OstmRole ostmRole) {
        return saveCreateHistory(ostmRole);
    }

    @SuppressWarnings("unused")
    @OstmCheck({CREATE})
    public Mono<AbstractMap.SimpleEntry<String, OstmModel>> createDocType(OstmDocType ostmDocType) {
        return saveCreateHistory(ostmDocType);
    }

    @SuppressWarnings("unused")
    @OstmCheck({CREATE})
    public Mono<AbstractMap.SimpleEntry<String, OstmModel>> createDocFlow(OstmDocFlow ostmDocFlow) {
        return saveCreateHistory(ostmDocFlow);
    }

    @SuppressWarnings("unused")
    @OstmCheck({CREATE})
    public Mono<AbstractMap.SimpleEntry<String, OstmModel>> createDoc(OstmDoc ostmDoc) {
        OstmDoc toSave = ostmDoc.clone();
        toSave.setContent(null);
        return saveCreateHistory(toSave);
    }

    @SuppressWarnings("unused")
    @OstmCheck({CREATE})
    public Mono<AbstractMap.SimpleEntry<String, OstmModel>> createDocFlow(OstmField ostmField) {
        return saveCreateHistory(ostmField);
    }

    private <T extends OstmModel> Mono<AbstractMap.SimpleEntry<String, T>> saveUpdateHistory(T oldModel, T ostmModel) {
        return ostmHistoryRepository.save(
                OstmHistory.builder()
                        .objectId(ostmModel.getId())
                        .objectType(ostmModel.getClass().getSimpleName())
                        .userId(OstmAuthUtils.getCurrentUser())
                        .oldValue(oldModel)
                        .newValue(ostmModel)
                        .build()
        ).then(Mono.empty());
    }

    @SuppressWarnings("unused")
    @OstmCheck({UPDATE})
    public Mono<AbstractMap.SimpleEntry<String, OstmModel>> updateProject(OstmProject ostmProject) {
        return ostmProjectRepository.findById(ostmProject.getId())
                .flatMap(oldOstmProject -> saveUpdateHistory(oldOstmProject, ostmProject));
    }

    @SuppressWarnings("unused")
    @OstmCheck({UPDATE})
    public Mono<AbstractMap.SimpleEntry<String, OstmModel>> updateUser(OstmUser ostmUser) {
        return ostmUserRepository.findById(ostmUser.getId())
                .flatMap(oldOstmProject -> saveUpdateHistory(oldOstmProject, ostmUser));
    }

    @SuppressWarnings("unused")
    @OstmCheck({UPDATE})
    public Mono<AbstractMap.SimpleEntry<String, OstmModel>> updateRole(OstmRole ostmUser) {
        return ostmRoleRepository.findById(ostmUser.getId())
                .flatMap(oldOstmProject -> saveUpdateHistory(oldOstmProject, ostmUser));
    }

    @SuppressWarnings("unused")
    @OstmCheck({UPDATE})
    public Mono<AbstractMap.SimpleEntry<String, OstmModel>> updateDocType(OstmDocType ostmDocType) {
        return ostmDocTypeRepository.findById(ostmDocType.getId())
                .flatMap(oldOstmProject -> saveUpdateHistory(oldOstmProject, ostmDocType));
    }

    @SuppressWarnings("unused")
    @OstmCheck({UPDATE})
    public Mono<AbstractMap.SimpleEntry<String, OstmModel>> updateDocFlow(OstmDocFlow ostmDocFlow) {
        return ostmDocFlowRepository.findById(ostmDocFlow.getId())
                .flatMap(oldOstmProject -> saveUpdateHistory(oldOstmProject, ostmDocFlow));
    }

    @SuppressWarnings("unused")
    @OstmCheck({UPDATE})
    public Mono<AbstractMap.SimpleEntry<String, OstmModel>> updateField(OstmField ostmField) {
        return ostmFieldRepository.findById(ostmField.getId())
                .flatMap(oldOstmProject -> saveUpdateHistory(oldOstmProject, ostmField));
    }

    @SuppressWarnings("unused")
    @OstmCheck({UPDATE})
    public Mono<AbstractMap.SimpleEntry<String, OstmModel>> updateDoc(OstmDoc ostmDoc) {
        OstmDoc toSave = ostmDoc.clone();
        toSave.setContent(null);

        return ostmDocRepository.findById(toSave.getId())
                .flatMap(oldOstmProject -> saveUpdateHistory(oldOstmProject, toSave));
    }

    @SuppressWarnings("unused")
    @OstmCheck({CREATE, UPDATE})
    public Mono<AbstractMap.SimpleEntry<String, OstmModel>> updateContent(OstmContent ostmContent) {
        return ostmContentRepository.findById(ostmContent.getId())
                .flatMap(oldContent -> saveUpdateHistory(oldContent, ostmContent))
                .switchIfEmpty(Mono.defer(() -> saveCreateHistory(ostmContent)))
                .then(Mono.empty());
    }
}
