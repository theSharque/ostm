package global.ostm.backend.flow;

import global.ostm.backend.core.*;
import global.ostm.backend.doc.OstmDoc;
import global.ostm.backend.doctype.OstmDocType;
import global.ostm.backend.doctype.OstmDocTypeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.AbstractMap;

import static global.ostm.backend.core.OstmBus.Crud.*;

@Service
public class OstmDocFlowService extends OstmService<OstmDocFlow> {

    private final OstmDocFlowRepository ostmDocFlowRepository;
    private final OstmDocTypeRepository ostmDocTypeRepository;

    protected OstmDocFlowService(OstmBus ostmBus, OstmDocFlowRepository ostmRepository,
            OstmDocFlowRepository ostmDocFlowRepository, OstmDocTypeRepository ostmDocTypeRepository) {
        super(ostmBus, ostmRepository);
        this.ostmDocFlowRepository = ostmDocFlowRepository;
        this.ostmDocTypeRepository = ostmDocTypeRepository;
    }

    @SuppressWarnings("unused")
    @OstmCheck({CREATE, UPDATE})
    public Mono<AbstractMap.SimpleEntry<String, OstmModel>> checkDocType(OstmDocType ostmProject) {
        return ostmRepository.existsById(ostmProject.getFlow())
                .flatMap(exists -> exists
                        ? Mono.empty()
                        : Mono.error(new OstmFieldIsIncorrect("flow")));
    }

    @SuppressWarnings("unused")
    @OstmCheck({CREATE, UPDATE})
    public Mono<AbstractMap.SimpleEntry<String, OstmModel>> checkDocStep(OstmDoc ostmDoc) {
        return ostmDocTypeRepository.findById(ostmDoc.getDocType())
                .flatMap(ostmDocType -> ostmDocFlowRepository.findById(ostmDocType.getFlow())
                        .flatMap(ostmDocFlow -> ostmDocFlow.getSteps().containsKey(ostmDoc.getStepId())
                                ? Mono.empty()
                                : Mono.error(new OstmStepImpossible())));
    }

    @SuppressWarnings("unused")
    @OstmCheck({READ})
    public Mono<AbstractMap.SimpleEntry<String, OstmDocFlowStep>> checkDocType(OstmDoc ostmDoc) {
        return ostmDocTypeRepository.findById(ostmDoc.getDocType())
                .flatMap(ostmDocType -> ostmDocFlowRepository.findById(ostmDocType.getFlow())
                        .flatMap(ostmDocFlow -> Flux.fromIterable(
                                        ostmDocFlow.getSteps().get(ostmDoc.getStepId()).getNext())
                                .map(id -> new AbstractMap.SimpleEntry<>(id, ostmDocFlow.getSteps().get(id)))
                                .collectMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue)
                                .map(nextStepsMap -> {
                                    OstmDocFlowStep steps = new OstmDocFlowStep();
                                    steps.setId(ostmDoc.getStepId());
                                    steps.setContent(nextStepsMap);

                                    return new AbstractMap.SimpleEntry<>("steps", steps);
                                })
                        )
                );
    }

    public Flux<OstmDocFlow> getDocFlow() {
        return ostmRepository.findAll(Sort.by("id").ascending());
    }

    public Mono<OstmDocFlow> getFields() {
        return super.fields(new OstmDocFlow());
    }

    public Mono<OstmDocFlow> getDocFlow(String id) {
        return super.read(id);
    }

    public Mono<OstmDocFlow> createDocFlow(OstmDocFlow ostmDocFlow) {
        return validateSteps(ostmDocFlow)
                .flatMap(incorrectSteps -> incorrectSteps == 0
                        ? super.create(ostmDocFlow)
                        : Mono.error(new OstmStepImpossible())
                );
    }

    public Mono<OstmDocFlow> updateDocFlow(String id, OstmDocFlow ostmDocFlow) {
        return validateSteps(ostmDocFlow)
                .flatMap(incorrectSteps -> incorrectSteps == 0
                        ? super.update(id, ostmDocFlow)
                        : Mono.error(new OstmStepImpossible())
                );
    }

    private Mono<Long> validateSteps(OstmDocFlow ostmDocFlow) {
        return Flux.fromIterable(ostmDocFlow.getSteps().values())
                .flatMap(ostmDocFlowStep -> ostmDocFlowStep.getNext() != null
                        ? Flux.fromIterable(ostmDocFlowStep.getNext()) : Mono.empty())
                .filter(step -> !ostmDocFlow.getSteps().containsKey(step))
                .count();
    }
}
