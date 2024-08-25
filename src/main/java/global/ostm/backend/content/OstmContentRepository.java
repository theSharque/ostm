package global.ostm.backend.content;

import global.ostm.backend.core.OstmRepository;
import reactor.core.publisher.Flux;

public interface OstmContentRepository extends OstmRepository<OstmContent> {

    Flux<OstmContent> findByDocId(String id);
}
