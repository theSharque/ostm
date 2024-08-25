package global.ostm.backend.flow;

import com.mongodb.lang.Nullable;
import global.ostm.backend.core.OstmModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collections;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
public class OstmDocFlowStep extends OstmModel {

    @Nullable
    private Set<String> roles = null;
    private Set<String> next = Collections.emptySet();
    @Nullable
    private String changes = null;
}
