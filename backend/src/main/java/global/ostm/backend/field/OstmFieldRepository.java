package global.ostm.backend.field;

import global.ostm.backend.core.OstmRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface OstmFieldRepository extends OstmRepository<OstmField> {

    Flux<OstmField> findAllByView(String viewType);

    Flux<OstmField> findAllByProjectKeyAndView(String projectId, String viewType);

    Mono<OstmField> findByViewAndId(String viewType, String id);
}
