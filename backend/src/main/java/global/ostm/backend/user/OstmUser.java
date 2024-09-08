package global.ostm.backend.user;

import com.mongodb.lang.Nullable;
import global.ostm.backend.core.OstmModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "user")
@EqualsAndHashCode(callSuper = true)
public class OstmUser extends OstmModel {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Nullable
    private List<String> roles;
}
