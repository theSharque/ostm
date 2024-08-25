package global.ostm.backend.core;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClientResponseException;

public class OstmObjectNotFound extends WebClientResponseException {

    private static final String OBJECT_NOT_EXISTS = "Object %s with ID %s doesn't exists";

    public OstmObjectNotFound(String type, String id) {
        super(HttpStatus.NOT_FOUND.value(), String.format(OBJECT_NOT_EXISTS, type, id), null, null, null);
    }
}
