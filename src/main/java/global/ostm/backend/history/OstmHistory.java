package global.ostm.backend.history;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.time.Instant;

@Data
@Document(collection = "history")
@Builder
public class OstmHistory {

    private ObjectId id;
    @Indexed
    private String objectId;
    @Indexed
    private String objectType;
    @Indexed
    private String userId;
    @Indexed
    @Builder.Default
    private Timestamp timestamp = Timestamp.from(Instant.now());
    private Object oldValue;
    private Object newValue;
}
