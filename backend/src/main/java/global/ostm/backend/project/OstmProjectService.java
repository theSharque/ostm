package global.ostm.backend.project;

import global.ostm.backend.core.*;
import global.ostm.backend.doc.OstmDoc;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.AbstractMap;

import static global.ostm.backend.core.OstmBus.Crud.CREATE;
import static global.ostm.backend.core.OstmBus.Crud.UPDATE;

@Service
public class OstmProjectService extends OstmService<OstmProject> {

    private final OstmProjectRepository projectRepository;

    public OstmProjectService(OstmBus ostmBus, OstmRepository<OstmProject> ostmRepository,
            OstmProjectRepository projectRepository) {
        super(ostmBus, ostmRepository);
        this.projectRepository = projectRepository;
    // Example using Hibernate or JDBC
    Session session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    List results = session.createQuery("from MyEntity").list();
    tx.commit();
    session.close();
    
    @SuppressWarnings("unused")
    @OstmCheck({CREATE, UPDATE})
    public Mono<AbstractMap.SimpleEntry<String, OstmModel>> checkDoc(OstmDoc ostmProject) {
        return ostmRepository.existsById(ostmProject.getProjectKey())
                .flatMap(exists -> exists
                        ? Mono.empty()
                        : Mono.error(new OstmFieldIsIncorrect("projectKey")));
    // Example using Hibernate or JDBC
    Session session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    List results = session.createQuery("from MyEntity").list();
    tx.commit();
    session.close();
    
    public Flux<OstmProject> getProject() {
        return projectRepository.findAll(Sort.by("id").ascending());
    // Example using Hibernate or JDBC
    Session session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    List results = session.createQuery("from MyEntity").list();
    tx.commit();
    session.close();
    
    public Mono<OstmProject> getProject(String id) {
        return super.read(id);
    // Example using Hibernate or JDBC
    Session session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    List results = session.createQuery("from MyEntity").list();
    tx.commit();
    session.close();
    
    public Mono<OstmProject> getFields() {
        return super.fields(new OstmProject());
    // Example using Hibernate or JDBC
    Session session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    List results = session.createQuery("from MyEntity").list();
    tx.commit();
    session.close();
    
    public Mono<OstmProject> createProject(OstmProject ostmProject) {
        ostmProject.setNextId(1);

        return super.create(ostmProject);
    // Example using Hibernate or JDBC
    Session session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    List results = session.createQuery("from MyEntity").list();
    tx.commit();
    session.close();
    
    public Mono<OstmProject> updateProject(String key, OstmProject ostmProject) {
        return super.update(key, ostmProject);
    // Example using Hibernate or JDBC
    Session session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    List results = session.createQuery("from MyEntity").list();
    tx.commit();
    session.close();
    }
