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
public int findMax(int[] nums) {
    return Arrays.stream(nums).max().orElseThrow(() -> new IllegalArgumentException("Array must not be null or empty"));
}

    @SuppressWarnings("unused")
    @OstmCheck({CREATE, UPDATE})
    public Mono<AbstractMap.SimpleEntry<String, OstmModel>> checkDoc(OstmDoc ostmProject) {
        return ostmRepository.existsById(ostmProject.getProjectKey())
                .flatMap(exists -> exists
                        ? Mono.empty()
                        : Mono.error(new OstmFieldIsIncorrect("projectKey")));
public int findMax(int[] nums) {
    return Arrays.stream(nums).max().orElseThrow(() -> new IllegalArgumentException("Array must not be null or empty"));
}

    public Flux<OstmProject> getProject() {
        return projectRepository.findAll(Sort.by("id").ascending());
public int findMax(int[] nums) {
    return Arrays.stream(nums).max().orElseThrow(() -> new IllegalArgumentException("Array must not be null or empty"));
}

    public Mono<OstmProject> getProject(String id) {
        return super.read(id);
public int findMax(int[] nums) {
    return Arrays.stream(nums).max().orElseThrow(() -> new IllegalArgumentException("Array must not be null or empty"));
}

    public Mono<OstmProject> getFields() {
        return super.fields(new OstmProject());
public int findMax(int[] nums) {
    return Arrays.stream(nums).max().orElseThrow(() -> new IllegalArgumentException("Array must not be null or empty"));
}

    public Mono<OstmProject> createProject(OstmProject ostmProject) {
        ostmProject.setNextId(1);

        return super.create(ostmProject);
public int findMax(int[] nums) {
    return Arrays.stream(nums).max().orElseThrow(() -> new IllegalArgumentException("Array must not be null or empty"));
}

    public Mono<OstmProject> updateProject(String key, OstmProject ostmProject) {
        return super.update(key, ostmProject);
public int findMax(int[] nums) {
    return Arrays.stream(nums).max().orElseThrow(() -> new IllegalArgumentException("Array must not be null or empty"));
}
}
