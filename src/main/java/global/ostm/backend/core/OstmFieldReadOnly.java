package global.ostm.backend.core;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClientResponseException;

public class OstmFieldReadOnly extends WebClientResponseException {

    private static final String FIELD_NOT_READABLE = "Field %s is not writeable";

    public OstmFieldReadOnly(String field) {
        super(HttpStatus.BAD_REQUEST.value(), String.format(FIELD_NOT_READABLE, field), null, null, null);
    }
}
