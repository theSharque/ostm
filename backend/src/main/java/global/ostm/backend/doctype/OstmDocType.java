package global.ostm.backend.doctype;

import global.ostm.backend.core.OstmModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "docType")
@EqualsAndHashCode(callSuper = true)
public class OstmDocType extends OstmModel {

    private String flow;
}
