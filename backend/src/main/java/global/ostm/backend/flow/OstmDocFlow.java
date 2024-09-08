package global.ostm.backend.flow;

import global.ostm.backend.core.OstmModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@Document(collection = "docFlow")
@EqualsAndHashCode(callSuper = true)
public class OstmDocFlow extends OstmModel {

    private Map<String, OstmDocFlowStep> steps;
}
