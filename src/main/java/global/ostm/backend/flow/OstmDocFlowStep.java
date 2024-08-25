package global.ostm.backend.flow;

import com.mongodb.lang.Nullable;
import lombok.Data;

import java.util.Collections;
import java.util.Set;

@Data
public class OstmDocFlowStep {

    @Nullable
    private Set<String> roles = null;
    private Set<String> next = Collections.emptySet();
    @Nullable
    private String changes = null;
}
