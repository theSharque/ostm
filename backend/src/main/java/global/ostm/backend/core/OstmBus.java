package global.ostm.backend.core;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class OstmBus {

    public enum Crud {FIELDS, CREATE, UPDATE, READ, DELETE}

    @AllArgsConstructor
    private static class CheckMethod {

        Object object;
        Method method;
    }

    private static final Map<Class<?>, Map<Crud, Set<CheckMethod>>> methods = new ConcurrentHashMap<>();

    @SuppressWarnings("unused")
    public void subscribe(Crud operation, Object object, Method method) {
        Class<?> clazz = method.getParameterTypes()[0];

        log.debug("Subscribing from {} to {} on {} -> {}", object.getClass().getSimpleName(), clazz.getSimpleName(),
                operation, method.getName());

        if (method.getParameterCount() != 1) {
            log.error("Wrong number of parameters for subscribe operation {} method {}", operation, method.getName());
            return;
        }

        if (method.getReturnType().isAssignableFrom(AbstractMap.SimpleEntry.class)) {
            log.error("Wrong return type for subscribe operation {} method {}", operation, method.getName());
        }

        methods.putIfAbsent(clazz, new ConcurrentHashMap<>());
        methods.get(clazz).putIfAbsent(operation, ConcurrentHashMap.newKeySet());
        methods.get(clazz).get(operation).add(new CheckMethod(object, method));
    }

    @SuppressWarnings("unused")
    public void unsubscribe(Crud operation, Object object, Method method) {
        Class<?> clazz = method.getParameterTypes()[0];

        log.debug("Unsubscribing from type {} with description {}", clazz.getSimpleName(), method);

        if (methods.containsKey(clazz)) {
            if (methods.get(clazz).containsKey(operation)) {
                methods.get(clazz).get(operation).remove(new CheckMethod(object, method));
            }
        }
    }

    @SuppressWarnings("all")
    public <T extends OstmModel> Mono<T> publish(Crud operation, T ostmModel) {
        Class<?> clazz = ostmModel.getClass();

        log.debug("Publishing type {} object {}", clazz.getSimpleName(), ostmModel);

        if (methods.containsKey(clazz) && !methods.get(clazz).isEmpty() && methods.get(clazz)
                .containsKey(operation) && !methods.get(clazz).get(operation).isEmpty()) {

            return Flux.fromIterable(methods.get(clazz).get(operation)).flatMap(checkMethod -> {
                        log.debug("Invoke object {} method {}", checkMethod.object.getClass().getSimpleName(),
                                checkMethod.method.getName());
                        Publisher<AbstractMap.SimpleEntry<String, ? extends OstmModel>> res = Flux.empty();
                        try {
                            res = (Publisher<AbstractMap.SimpleEntry<String, ? extends OstmModel>>) checkMethod.method.invoke(
                                    checkMethod.object, ostmModel);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            log.error("Error invoking object {} method {} with object {}",
                                    checkMethod.object.getClass().getSimpleName(), checkMethod.method.getName(), ostmModel, e);
                        }

                        return res;
                    }).collectMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue)
                    .map(objectObjectMap -> {
                        if (!objectObjectMap.isEmpty()) {
                            ostmModel.setContent(objectObjectMap);
                        }

                        return ostmModel;
                    });
        } else {
            return Mono.just(ostmModel);
        }
    }
}
