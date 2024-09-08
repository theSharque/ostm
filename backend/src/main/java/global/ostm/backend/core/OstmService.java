package global.ostm.backend.core;

import reactor.core.publisher.Mono;

import java.util.Arrays;

import static global.ostm.backend.core.OstmBus.Crud.CREATE;
import static global.ostm.backend.core.OstmBus.Crud.UPDATE;

public abstract class OstmService<T extends OstmModel> {

    protected final OstmBus ostmBus;
    protected final OstmRepository<T> ostmRepository;

    protected OstmService(OstmBus ostmBus, OstmRepository<T> ostmRepository) {
        this.ostmBus = ostmBus;
        this.ostmRepository = ostmRepository;

        installBus();
    }

    protected final void installBus() {
        Arrays.stream(this.getClass().getDeclaredMethods())
                .filter(method -> method.getAnnotation(OstmCheck.class) != null)
                .forEach(method -> Arrays.stream(method.getAnnotation(OstmCheck.class).value())
                        .forEach(crud -> ostmBus.subscribe(crud, this, method)));
    }

    protected Mono<T> fields(T ostmObject) {
        return ostmBus.publish(OstmBus.Crud.FIELDS, ostmObject);
    }

    protected Mono<T> read(String id) {
        return ostmRepository.findById(id).flatMap(ostmObject -> ostmBus.publish(OstmBus.Crud.READ, ostmObject));
    }

    protected Mono<T> read(T ostmObject) {
        return ostmBus.publish(OstmBus.Crud.READ, ostmObject);
    }

    protected Mono<T> create(T ostmObject) {
        return ostmRepository.existsById(ostmObject.getId()).flatMap(exists -> exists
                ? Mono.error(new OstmObjectExists(ostmObject.getClass().getSimpleName(), ostmObject.getId()))
                : ostmBus.publish(CREATE, ostmObject).flatMap(ostmRepository::save));
    }

    protected Mono<T> update(String id, T ostmObject) {
        return !id.equals(ostmObject.getId())
                ? Mono.error(new OstmFieldReadOnly("id"))
                : ostmRepository.existsById(id).flatMap(exists -> exists
                        ? ostmBus.publish(UPDATE, ostmObject).flatMap(ostmRepository::save)
                        : Mono.error(new OstmObjectNotFound(ostmObject.getClass().getSimpleName(), id))
                );
    }
}
