package global.ostm.backend.flow;

import global.ostm.backend.core.OstmBus;
import global.ostm.backend.core.OstmCheck;
import global.ostm.backend.core.OstmFieldIsIncorrect;
import global.ostm.backend.core.OstmService;
import global.ostm.backend.doctype.OstmDocType;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.AbstractMap;

import static global.ostm.backend.core.OstmBus.Crud.CREATE;
import static global.ostm.backend.core.OstmBus.Crud.UPDATE;

@Service
public class OstmDocFlowService extends OstmService<OstmDocFlow> {

    protected OstmDocFlowService(OstmBus ostmBus, OstmDocFlowRepository ostmRepository) {
        super(ostmBus, ostmRepository);
    }

    @SuppressWarnings("unused")
    @OstmCheck({CREATE, UPDATE})
    public Mono<AbstractMap.SimpleEntry<String, OstmDocType>> checkDocType(OstmDocType ostmProject) {
        return ostmRepository.existsById(ostmProject.getFlow())
                .flatMap(exists -> exists
                        ? Mono.empty()
                        : Mono.error(new OstmFieldIsIncorrect("flow")));
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
