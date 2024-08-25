package global.ostm.backend.content;

import global.ostm.backend.core.*;
import global.ostm.backend.doc.OstmDoc;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.AbstractMap;

import static global.ostm.backend.core.OstmBus.Crud.*;

@Service
public class OstmContentService extends OstmService<OstmContent> {

    OstmContentRepository contentRepository;

    protected OstmContentService(OstmBus ostmBus, OstmContentRepository contentRepository) {
        super(ostmBus, contentRepository);

        this.contentRepository = contentRepository;
    }

    @SuppressWarnings("unused")
    @OstmCheck({READ})
    public Flux<AbstractMap.SimpleEntry<String, OstmContent>> readDoc(OstmDoc ostmDoc) {
        return contentRepository.findByDocId(ostmDoc.getId())
                .map(ostmContent -> new AbstractMap.SimpleEntry<>(ostmContent.getFieldId(), ostmContent));
    }

    @SuppressWarnings("unused")
    @OstmCheck({CREATE, UPDATE})
    public Mono<AbstractMap.SimpleEntry<String, OstmModel>> saveDoc(OstmDoc ostmDoc) {
        return ostmDoc.getContent() == null ? Mono.empty()
                : Flux.fromIterable(ostmDoc.getContent().values())
                        .filter(ostmModel -> ostmModel instanceof OstmContent)
                        .map(ostmModel -> (OstmContent) ostmModel)
                        .flatMap(ostmContent -> {
                            ostmContent.setId(ostmDoc.getId() + "_" + ostmContent.getFieldId());
                            ostmContent.setDocId(ostmDoc.getId());
                            ostmContent.setHash(CryptUtils.encryptMd5(ostmContent.getData()));
                            return ostmBus.publish(UPDATE, ostmContent);
                        })
                        .collectList()
                        .flatMapMany(contentRepository::saveAll)
                        .count()
                        .then(Mono.empty());
    }
}
