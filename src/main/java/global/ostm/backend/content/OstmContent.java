package global.ostm.backend.content;

import global.ostm.backend.core.OstmModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "content")
@EqualsAndHashCode(callSuper = true)
public class OstmContent extends OstmModel {

    @Indexed
    private String docId;
    @Indexed
    private String fieldId;
    private String data;
    private String hash;
}
