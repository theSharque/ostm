package global.ostm.backend.project;

import global.ostm.backend.core.OstmRepository;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface OstmProjectRepository extends OstmRepository<OstmProject> {

    @Update("{ '$inc' : { 'NextId' : 1 } }")
    Mono<Long> findAndIncrementNextIdById(String id);
}
