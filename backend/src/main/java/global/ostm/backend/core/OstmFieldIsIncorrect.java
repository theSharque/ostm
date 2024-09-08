package global.ostm.backend.core;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClientResponseException;

public class OstmFieldIsIncorrect extends WebClientResponseException {

    private static final String FIELD_IS_INCORRECT = "Field %s has incorrect value";

    public OstmFieldIsIncorrect(String field) {
        super(HttpStatus.BAD_REQUEST.value(), String.format(FIELD_IS_INCORRECT, field), null, null, null);
    }
}
