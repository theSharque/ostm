package global.ostm.backend.field;

import com.mongodb.lang.Nullable;
import global.ostm.backend.core.OstmModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "field")
@EqualsAndHashCode(callSuper = true)
public class OstmField extends OstmModel {

    @Indexed
    private String name;
    @Nullable
    @Indexed
    private String projectKey;
    @Indexed
    private String view;
    private OstmFieldType type;
    @Nullable
    private String changes;

    public String getId() {
        return projectKey + "_" + view + "_" + name;
    }
}
