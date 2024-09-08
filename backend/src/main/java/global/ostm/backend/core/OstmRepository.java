package global.ostm.backend.core;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface OstmRepository<T extends OstmModel> extends ReactiveMongoRepository<T, String> {

}
