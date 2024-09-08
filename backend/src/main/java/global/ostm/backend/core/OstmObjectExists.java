package global.ostm.backend.core;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClientResponseException;

public class OstmObjectExists extends WebClientResponseException {

    private static final String OBJECT_EXISTS = "Object %s with ID %s already exists";

    public OstmObjectExists(String type, String id) {
        super(HttpStatus.BAD_REQUEST.value(), String.format(OBJECT_EXISTS, type, id), null, null, null);
    }
}
