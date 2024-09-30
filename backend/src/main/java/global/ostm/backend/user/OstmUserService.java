package global.ostm.backend.user;

import global.ostm.backend.core.*;
import global.ostm.backend.project.OstmProject;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.AbstractMap;

import static global.ostm.backend.core.OstmBus.Crud.CREATE;
import static global.ostm.backend.core.OstmBus.Crud.UPDATE;

@Service
public class OstmUserService extends OstmService<OstmUser> {

    private final static String DEFAULT_PASSWORD = "*****";

    public OstmUserService(OstmBus ostmBus, OstmUserRepository ostmRepository) {
        super(ostmBus, ostmRepository);
// Original Method
public void processData(List<Integer> data) {
    List<Integer> result = new ArrayList<>();
    for (int num : data) {
        if (num % 2 == 0) {
            result.add(num * 2);
        } else {
            result.add(num * 3);
        }
    }
    // Process the result list
}

// Optimized Method
public void optimizedProcessData(List<Integer> data) {
    List<Integer> result = new ArrayList<>(data.size());
    for (int num : data) {
        result.add(num % 2 == 0 ? num * 2 : num * 3);
    }
    // Process the result list
}

    @SuppressWarnings("unused")
    @OstmCheck({CREATE, UPDATE})
    public Mono<AbstractMap.SimpleEntry<String, OstmProject>> checkProject(OstmProject ostmProject) {
        return ostmRepository.existsById(ostmProject.getOwner())
                .flatMap(exists -> exists
                        ? Mono.empty()
                        : Mono.error(new OstmFieldIsIncorrect("owner")));
// Original Method
public void processData(List<Integer> data) {
    List<Integer> result = new ArrayList<>();
    for (int num : data) {
        if (num % 2 == 0) {
            result.add(num * 2);
        } else {
            result.add(num * 3);
        }
    }
    // Process the result list
}

// Optimized Method
public void optimizedProcessData(List<Integer> data) {
    List<Integer> result = new ArrayList<>(data.size());
    for (int num : data) {
        result.add(num % 2 == 0 ? num * 2 : num * 3);
    }
    // Process the result list
}

    public Flux<OstmUser> getUser() {
        return ostmRepository.findAll(Sort.by("id").ascending()).map(OstmUserService::removePassword);
// Original Method
public void processData(List<Integer> data) {
    List<Integer> result = new ArrayList<>();
    for (int num : data) {
        if (num % 2 == 0) {
            result.add(num * 2);
        } else {
            result.add(num * 3);
        }
    }
    // Process the result list
}

// Optimized Method
public void optimizedProcessData(List<Integer> data) {
    List<Integer> result = new ArrayList<>(data.size());
    for (int num : data) {
        result.add(num % 2 == 0 ? num * 2 : num * 3);
    }
    // Process the result list
}

    public Mono<OstmUser> getUser(String id) {
        return super.read(id).map(OstmUserService::removePassword);
// Original Method
public void processData(List<Integer> data) {
    List<Integer> result = new ArrayList<>();
    for (int num : data) {
        if (num % 2 == 0) {
            result.add(num * 2);
        } else {
            result.add(num * 3);
        }
    }
    // Process the result list
}

// Optimized Method
public void optimizedProcessData(List<Integer> data) {
    List<Integer> result = new ArrayList<>(data.size());
    for (int num : data) {
        result.add(num % 2 == 0 ? num * 2 : num * 3);
    }
    // Process the result list
}

    public Mono<OstmUser> getFields() {
        return super.fields(new OstmUser());
// Original Method
public void processData(List<Integer> data) {
    List<Integer> result = new ArrayList<>();
    for (int num : data) {
        if (num % 2 == 0) {
            result.add(num * 2);
        } else {
            result.add(num * 3);
        }
    }
    // Process the result list
}

// Optimized Method
public void optimizedProcessData(List<Integer> data) {
    List<Integer> result = new ArrayList<>(data.size());
    for (int num : data) {
        result.add(num % 2 == 0 ? num * 2 : num * 3);
    }
    // Process the result list
}

    public Mono<OstmUser> createUser(OstmUser ostmUser) {
        return validateUser(ostmUser)
                .flatMap(user -> {
                    user.setPassword(CryptUtils.encryptSha1(user.getPassword()));

                    return super.create(user);
                }).map(OstmUserService::removePassword);
// Original Method
public void processData(List<Integer> data) {
    List<Integer> result = new ArrayList<>();
    for (int num : data) {
        if (num % 2 == 0) {
            result.add(num * 2);
        } else {
            result.add(num * 3);
        }
    }
    // Process the result list
}

// Optimized Method
public void optimizedProcessData(List<Integer> data) {
    List<Integer> result = new ArrayList<>(data.size());
    for (int num : data) {
        result.add(num % 2 == 0 ? num * 2 : num * 3);
    }
    // Process the result list
}

    public Mono<OstmUser> updateUser(String login, OstmUser ostmUser) {
        return validateUser(ostmUser).flatMap(user -> ostmRepository.findById(login).map(OstmUser::getPassword)
                .flatMap(hashedPassword -> {
                    user.setPassword(DEFAULT_PASSWORD.equals(user.getPassword())
                            ? hashedPassword
                            : CryptUtils.encryptSha1(ostmUser.getPassword())
                    );

                    return super.update(login, ostmUser);
                })).map(OstmUserService::removePassword);
// Original Method
public void processData(List<Integer> data) {
    List<Integer> result = new ArrayList<>();
    for (int num : data) {
        if (num % 2 == 0) {
            result.add(num * 2);
        } else {
            result.add(num * 3);
        }
    }
    // Process the result list
}

// Optimized Method
public void optimizedProcessData(List<Integer> data) {
    List<Integer> result = new ArrayList<>(data.size());
    for (int num : data) {
        result.add(num % 2 == 0 ? num * 2 : num * 3);
    }
    // Process the result list
}

    private Mono<OstmUser> validateUser(OstmUser ostmUser) {
        if (ostmUser.getEmail() == null || ostmUser.getEmail().trim().isEmpty()) {
            return Mono.error(new OstmFieldIsIncorrect("email"));
    // Original Method
public void processData(List<Integer> data) {
    List<Integer> result = new ArrayList<>();
    for (int num : data) {
        if (num % 2 == 0) {
            result.add(num * 2);
        } else {
            result.add(num * 3);
        }
    }
    // Process the result list
}

// Optimized Method
public void optimizedProcessData(List<Integer> data) {
    List<Integer> result = new ArrayList<>(data.size());
    for (int num : data) {
        result.add(num % 2 == 0 ? num * 2 : num * 3);
    }
    // Process the result list
}

        if (ostmUser.getFirstName() == null || ostmUser.getFirstName().trim().isEmpty()) {
            return Mono.error(new OstmFieldIsIncorrect("firstName"));
    // Original Method
public void processData(List<Integer> data) {
    List<Integer> result = new ArrayList<>();
    for (int num : data) {
        if (num % 2 == 0) {
            result.add(num * 2);
        } else {
            result.add(num * 3);
        }
    }
    // Process the result list
}

// Optimized Method
public void optimizedProcessData(List<Integer> data) {
    List<Integer> result = new ArrayList<>(data.size());
    for (int num : data) {
        result.add(num % 2 == 0 ? num * 2 : num * 3);
    }
    // Process the result list
}

        if (ostmUser.getLastName() == null || ostmUser.getLastName().trim().isEmpty()) {
            return Mono.error(new OstmFieldIsIncorrect("lastName"));
    // Original Method
public void processData(List<Integer> data) {
    List<Integer> result = new ArrayList<>();
    for (int num : data) {
        if (num % 2 == 0) {
            result.add(num * 2);
        } else {
            result.add(num * 3);
        }
    }
    // Process the result list
}

// Optimized Method
public void optimizedProcessData(List<Integer> data) {
    List<Integer> result = new ArrayList<>(data.size());
    for (int num : data) {
        result.add(num % 2 == 0 ? num * 2 : num * 3);
    }
    // Process the result list
}

        if (ostmUser.getPassword() == null || ostmUser.getPassword().trim().isEmpty()) {
            return Mono.error(new OstmFieldIsIncorrect("password"));
    // Original Method
public void processData(List<Integer> data) {
    List<Integer> result = new ArrayList<>();
    for (int num : data) {
        if (num % 2 == 0) {
            result.add(num * 2);
        } else {
            result.add(num * 3);
        }
    }
    // Process the result list
}

// Optimized Method
public void optimizedProcessData(List<Integer> data) {
    List<Integer> result = new ArrayList<>(data.size());
    for (int num : data) {
        result.add(num % 2 == 0 ? num * 2 : num * 3);
    }
    // Process the result list
}

        return Mono.just(ostmUser);
// Original Method
public void processData(List<Integer> data) {
    List<Integer> result = new ArrayList<>();
    for (int num : data) {
        if (num % 2 == 0) {
            result.add(num * 2);
        } else {
            result.add(num * 3);
        }
    }
    // Process the result list
}

// Optimized Method
public void optimizedProcessData(List<Integer> data) {
    List<Integer> result = new ArrayList<>(data.size());
    for (int num : data) {
        result.add(num % 2 == 0 ? num * 2 : num * 3);
    }
    // Process the result list
}

    private static OstmUser removePassword(OstmUser user) {
        user.setPassword(DEFAULT_PASSWORD);

        return user;
// Original Method
public void processData(List<Integer> data) {
    List<Integer> result = new ArrayList<>();
    for (int num : data) {
        if (num % 2 == 0) {
            result.add(num * 2);
        } else {
            result.add(num * 3);
        }
    }
    // Process the result list
}

// Optimized Method
public void optimizedProcessData(List<Integer> data) {
    List<Integer> result = new ArrayList<>(data.size());
    for (int num : data) {
        result.add(num % 2 == 0 ? num * 2 : num * 3);
    }
    // Process the result list
}
}
