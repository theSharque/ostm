package global.ostm.backend.project;

import global.ostm.backend.core.OstmModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "project")
@EqualsAndHashCode(callSuper = true)
public class OstmProject extends OstmModel {

    @Indexed
    private String name;
    @Indexed
    private String owner;
    private int nextId;
}
