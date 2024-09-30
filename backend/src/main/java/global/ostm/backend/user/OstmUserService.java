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
    }

    @SuppressWarnings("unused")
    @OstmCheck({CREATE, UPDATE})
    public Mono<AbstractMap.SimpleEntry<String, OstmProject>> checkProject(OstmProject ostmProject) {
        return ostmRepository.existsById(ostmProject.getOwner())
                .flatMap(exists -> exists
                        ? Mono.empty()
                        : Mono.error(new OstmFieldIsIncorrect("owner")));
    }

    public Flux<OstmUser> getUser() {
        return ostmRepository.findAll(Sort.by("id").ascending()).map(OstmUserService::removePassword);
    }

    public Mono<OstmUser> getUser(String id) {
        return super.read(id).map(OstmUserService::removePassword);
    }

    public Mono<OstmUser> getFields() {
        return super.fields(new OstmUser());
    }

    public Mono<OstmUser> createUser(OstmUser ostmUser) {
        return validateUser(ostmUser)
                .flatMap(user -> {
                    user.setPassword(CryptUtils.encryptSha1(user.getPassword()));

                    return super.create(user);
                }).map(OstmUserService::removePassword);
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
    }

    private Mono<OstmUser> validateUser(OstmUser ostmUser) {
        if (ostmUser.getEmail() == null || ostmUser.getEmail().trim().isEmpty()) {
            return Mono.error(new OstmFieldIsIncorrect("email"));
public void benchmark() {
    List<Integer> input = generateLargeList(100000); // Generate a large list for testing

    long startTime = System.nanoTime();
    processList(input);
    long endTime = System.nanoTime();
    System.out.println("Time taken by naive method: " + (endTime - startTime) + " ns");

    startTime = System.nanoTime();
    optimizedProcessList(input);
    endTime = System.nanoTime();
    System.out.println("Time taken by optimized method: " + (endTime - startTime) + " ns");
}

        if (ostmUser.getFirstName() == null || ostmUser.getFirstName().trim().isEmpty()) {
            return Mono.error(new OstmFieldIsIncorrect("firstName"));
public void benchmark() {
    List<Integer> input = generateLargeList(100000); // Generate a large list for testing

    long startTime = System.nanoTime();
    processList(input);
    long endTime = System.nanoTime();
    System.out.println("Time taken by naive method: " + (endTime - startTime) + " ns");

    startTime = System.nanoTime();
    optimizedProcessList(input);
    endTime = System.nanoTime();
    System.out.println("Time taken by optimized method: " + (endTime - startTime) + " ns");
}

        if (ostmUser.getLastName() == null || ostmUser.getLastName().trim().isEmpty()) {
            return Mono.error(new OstmFieldIsIncorrect("lastName"));
public void benchmark() {
    List<Integer> input = generateLargeList(100000); // Generate a large list for testing

    long startTime = System.nanoTime();
    processList(input);
    long endTime = System.nanoTime();
    System.out.println("Time taken by naive method: " + (endTime - startTime) + " ns");

    startTime = System.nanoTime();
    optimizedProcessList(input);
    endTime = System.nanoTime();
    System.out.println("Time taken by optimized method: " + (endTime - startTime) + " ns");
}

        if (ostmUser.getPassword() == null || ostmUser.getPassword().trim().isEmpty()) {
            return Mono.error(new OstmFieldIsIncorrect("password"));
public void benchmark() {
    List<Integer> input = generateLargeList(100000); // Generate a large list for testing

    long startTime = System.nanoTime();
    processList(input);
    long endTime = System.nanoTime();
    System.out.println("Time taken by naive method: " + (endTime - startTime) + " ns");

    startTime = System.nanoTime();
    optimizedProcessList(input);
    endTime = System.nanoTime();
    System.out.println("Time taken by optimized method: " + (endTime - startTime) + " ns");
}

        return Mono.just(ostmUser);
    }

    private static OstmUser removePassword(OstmUser user) {
        user.setPassword(DEFAULT_PASSWORD);

        return user;
    }
}
