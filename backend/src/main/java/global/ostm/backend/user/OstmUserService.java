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
public int calculateSum(int[] numbers) {
    if (numbers == null || numbers.length == 0) {
        throw new IllegalArgumentException("The array must not be null or empty");
    }
    
    return Arrays.stream(numbers).parallel().sum(); // Use parallel stream for potentially faster computation
}

    @SuppressWarnings("unused")
    @OstmCheck({CREATE, UPDATE})
    public Mono<AbstractMap.SimpleEntry<String, OstmProject>> checkProject(OstmProject ostmProject) {
        return ostmRepository.existsById(ostmProject.getOwner())
                .flatMap(exists -> exists
                        ? Mono.empty()
                        : Mono.error(new OstmFieldIsIncorrect("owner")));
public int calculateSum(int[] numbers) {
    if (numbers == null || numbers.length == 0) {
        throw new IllegalArgumentException("The array must not be null or empty");
    }
    
    return Arrays.stream(numbers).parallel().sum(); // Use parallel stream for potentially faster computation
}

    public Flux<OstmUser> getUser() {
        return ostmRepository.findAll(Sort.by("id").ascending()).map(OstmUserService::removePassword);
public int calculateSum(int[] numbers) {
    if (numbers == null || numbers.length == 0) {
        throw new IllegalArgumentException("The array must not be null or empty");
    }
    
    return Arrays.stream(numbers).parallel().sum(); // Use parallel stream for potentially faster computation
}

    public Mono<OstmUser> getUser(String id) {
        return super.read(id).map(OstmUserService::removePassword);
public int calculateSum(int[] numbers) {
    if (numbers == null || numbers.length == 0) {
        throw new IllegalArgumentException("The array must not be null or empty");
    }
    
    return Arrays.stream(numbers).parallel().sum(); // Use parallel stream for potentially faster computation
}

    public Mono<OstmUser> getFields() {
        return super.fields(new OstmUser());
public int calculateSum(int[] numbers) {
    if (numbers == null || numbers.length == 0) {
        throw new IllegalArgumentException("The array must not be null or empty");
    }
    
    return Arrays.stream(numbers).parallel().sum(); // Use parallel stream for potentially faster computation
}

    public Mono<OstmUser> createUser(OstmUser ostmUser) {
        return validateUser(ostmUser)
                .flatMap(user -> {
                    user.setPassword(CryptUtils.encryptSha1(user.getPassword()));

                    return super.create(user);
                }).map(OstmUserService::removePassword);
public int calculateSum(int[] numbers) {
    if (numbers == null || numbers.length == 0) {
        throw new IllegalArgumentException("The array must not be null or empty");
    }
    
    return Arrays.stream(numbers).parallel().sum(); // Use parallel stream for potentially faster computation
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
public int calculateSum(int[] numbers) {
    if (numbers == null || numbers.length == 0) {
        throw new IllegalArgumentException("The array must not be null or empty");
    }
    
    return Arrays.stream(numbers).parallel().sum(); // Use parallel stream for potentially faster computation
}

    private Mono<OstmUser> validateUser(OstmUser ostmUser) {
        if (ostmUser.getEmail() == null || ostmUser.getEmail().trim().isEmpty()) {
            return Mono.error(new OstmFieldIsIncorrect("email"));
    public int calculateSum(int[] numbers) {
    if (numbers == null || numbers.length == 0) {
        throw new IllegalArgumentException("The array must not be null or empty");
    }
    
    return Arrays.stream(numbers).parallel().sum(); // Use parallel stream for potentially faster computation
}

        if (ostmUser.getFirstName() == null || ostmUser.getFirstName().trim().isEmpty()) {
            return Mono.error(new OstmFieldIsIncorrect("firstName"));
    public int calculateSum(int[] numbers) {
    if (numbers == null || numbers.length == 0) {
        throw new IllegalArgumentException("The array must not be null or empty");
    }
    
    return Arrays.stream(numbers).parallel().sum(); // Use parallel stream for potentially faster computation
}

        if (ostmUser.getLastName() == null || ostmUser.getLastName().trim().isEmpty()) {
            return Mono.error(new OstmFieldIsIncorrect("lastName"));
    public int calculateSum(int[] numbers) {
    if (numbers == null || numbers.length == 0) {
        throw new IllegalArgumentException("The array must not be null or empty");
    }
    
    return Arrays.stream(numbers).parallel().sum(); // Use parallel stream for potentially faster computation
}

        if (ostmUser.getPassword() == null || ostmUser.getPassword().trim().isEmpty()) {
            return Mono.error(new OstmFieldIsIncorrect("password"));
    public int calculateSum(int[] numbers) {
    if (numbers == null || numbers.length == 0) {
        throw new IllegalArgumentException("The array must not be null or empty");
    }
    
    return Arrays.stream(numbers).parallel().sum(); // Use parallel stream for potentially faster computation
}

        return Mono.just(ostmUser);
public int calculateSum(int[] numbers) {
    if (numbers == null || numbers.length == 0) {
        throw new IllegalArgumentException("The array must not be null or empty");
    }
    
    return Arrays.stream(numbers).parallel().sum(); // Use parallel stream for potentially faster computation
}

    private static OstmUser removePassword(OstmUser user) {
        user.setPassword(DEFAULT_PASSWORD);

        return user;
public int calculateSum(int[] numbers) {
    if (numbers == null || numbers.length == 0) {
        throw new IllegalArgumentException("The array must not be null or empty");
    }
    
    return Arrays.stream(numbers).parallel().sum(); // Use parallel stream for potentially faster computation
}
}
