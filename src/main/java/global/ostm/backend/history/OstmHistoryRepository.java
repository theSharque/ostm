package global.ostm.backend.history;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface OstmHistoryRepository extends ReactiveMongoRepository<OstmHistory, String> {

}
