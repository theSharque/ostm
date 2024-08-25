package global.ostm.backend.flow;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpStatusCodeException;

public class OstmStepImpossible extends HttpStatusCodeException {

    private static final String STEP_IMPOSSIBLE = "Steps Impossible";

    public OstmStepImpossible() {
        super(HttpStatusCode.valueOf(400), STEP_IMPOSSIBLE);
    }
}
