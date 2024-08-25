package global.ostm.backend.doc;

import global.ostm.backend.core.OstmModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "doc")
@EqualsAndHashCode(callSuper = true)
public class OstmDoc extends OstmModel implements Cloneable {

    @TextIndexed
    private String title;
    @TextIndexed
    private String description;
    @Indexed
    private String projectKey;
    @Indexed
    private String docType;
    private String stepId;

    @Override
    public OstmDoc clone() {
        try {
            return (OstmDoc) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
