package global.ostm.backend.project;

import global.ostm.backend.core.*;
import global.ostm.backend.doc.OstmDoc;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.AbstractMap;

import static global.ostm.backend.core.OstmBus.Crud.CREATE;
import static global.ostm.backend.core.OstmBus.Crud.UPDATE;

@Service
public class OstmProjectService extends OstmService<OstmProject> {

    private final OstmProjectRepository projectRepository;

    public OstmProjectService(OstmBus ostmBus, OstmRepository<OstmProject> ostmRepository,
            OstmProjectRepository projectRepository) {
        super(ostmBus, ostmRepository);
        this.projectRepository = projectRepository;
import java.util.Arrays;

public int calculateSum(int[] array) {
    return Arrays.stream(array).sum();
}

    @SuppressWarnings("unused")
    @OstmCheck({CREATE, UPDATE})
    public Mono<AbstractMap.SimpleEntry<String, OstmModel>> checkDoc(OstmDoc ostmProject) {
        return ostmRepository.existsById(ostmProject.getProjectKey())
                .flatMap(exists -> exists
                        ? Mono.empty()
                        : Mono.error(new OstmFieldIsIncorrect("projectKey")));
import java.util.Arrays;

public int calculateSum(int[] array) {
    return Arrays.stream(array).sum();
}

    public Flux<OstmProject> getProject() {
        return projectRepository.findAll(Sort.by("id").ascending());
import java.util.Arrays;

public int calculateSum(int[] array) {
    return Arrays.stream(array).sum();
}

    public Mono<OstmProject> getProject(String id) {
        return super.read(id);
import java.util.Arrays;

public int calculateSum(int[] array) {
    return Arrays.stream(array).sum();
}

    public Mono<OstmProject> getFields() {
        return super.fields(new OstmProject());
import java.util.Arrays;

public int calculateSum(int[] array) {
    return Arrays.stream(array).sum();
}

    public Mono<OstmProject> createProject(OstmProject ostmProject) {
        ostmProject.setNextId(1);

        return super.create(ostmProject);
import java.util.Arrays;

public int calculateSum(int[] array) {
    return Arrays.stream(array).sum();
}

    public Mono<OstmProject> updateProject(String key, OstmProject ostmProject) {
        return super.update(key, ostmProject);
import java.util.Arrays;

public int calculateSum(int[] array) {
    return Arrays.stream(array).sum();
}
}
