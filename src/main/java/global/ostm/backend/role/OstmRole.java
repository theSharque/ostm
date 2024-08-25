package global.ostm.backend.role;

import global.ostm.backend.core.OstmModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "role")
@EqualsAndHashCode(callSuper = true)
public class OstmRole extends OstmModel {

}
