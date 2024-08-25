package global.ostm.backend.role;

import global.ostm.backend.core.*;
import global.ostm.backend.flow.OstmDocFlow;
import global.ostm.backend.user.OstmUser;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.AbstractMap;
import java.util.stream.Collectors;

import static global.ostm.backend.core.OstmBus.Crud.CREATE;
import static global.ostm.backend.core.OstmBus.Crud.UPDATE;

@Service
public class OstmRoleService extends OstmService<OstmRole> {

    protected OstmRoleService(OstmBus ostmBus,
            OstmRepository<OstmRole> ostmRepository) {
        super(ostmBus, ostmRepository);
    }

    @SuppressWarnings("unused")
    @OstmCheck({CREATE, UPDATE})
    public Mono<AbstractMap.SimpleEntry<String, OstmUser>> checkUser(OstmUser ostmUser) {
        if (ostmUser.getRoles() == null || ostmUser.getRoles().isEmpty()) {
            return Mono.empty();
        } else {
            return ostmRepository.findAllById(ostmUser.getRoles()).count()
                    .flatMap(cnt -> cnt == ostmUser.getRoles().size()
                            ? Mono.empty()
                            : Mono.error(new OstmFieldIsIncorrect("roles")));
        }
    }

    @SuppressWarnings("unused")
    @OstmCheck({CREATE, UPDATE})
    public Mono<AbstractMap.SimpleEntry<String, OstmDocFlow>> checkDocFlow(OstmDocFlow ostmDocFlow) {
        return Flux.fromIterable(ostmDocFlow.getSteps().values())
                .flatMap(ostmDocFlowStep -> ostmDocFlowStep.getRoles() == null
                        ? Mono.empty()
                        : Flux.fromIterable(ostmDocFlowStep.getRoles()))
                .collect(Collectors.toSet())
                .flatMap(ostmRoles -> ostmRepository.findAllById(ostmRoles).count()
                        .flatMap(cnt -> cnt == ostmRoles.size()
                                ? Mono.empty()
                                : Mono.error(new OstmFieldIsIncorrect("roles"))));
    }

    public Flux<OstmRole> getRole() {
        return ostmRepository.findAll(Sort.by("id").ascending());
    }

    public Mono<OstmRole> getFields() {
        return super.fields(new OstmRole());
    }

    public Mono<OstmRole> getRole(String name) {
        return super.read(name);
    }

    public Mono<OstmRole> createRole(OstmRole ostmRole) {
        return super.create(ostmRole);
    }

    public Mono<OstmRole> updateRole(String name, OstmRole ostmRole) {
        return super.update(name, ostmRole);
    }
}
