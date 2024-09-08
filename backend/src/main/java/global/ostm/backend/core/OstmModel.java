package global.ostm.backend.core;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mongodb.lang.Nullable;
import global.ostm.backend.content.OstmContent;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Map;

@Data
//@JsonInclude(NON_NULL)
public abstract class OstmModel {

    @Id
    private String id;

    @Nullable
    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, defaultImpl = OstmContent.class)
    private Map<String, ? extends OstmModel> content = null;
}
