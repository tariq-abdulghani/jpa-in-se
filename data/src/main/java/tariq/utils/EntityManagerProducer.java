package tariq.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Closeable;
import java.io.IOException;

public class EntityManagerProducer implements Closeable {
    private static final String PERSISTENCE_UNIT_NAME = "PU";
    private static EntityManagerFactory factory; // must be closed

    public EntityManagerProducer(){
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public EntityManagerProducer(String persistenceUnitName){
        factory = Persistence.createEntityManagerFactory(persistenceUnitName);
    }
    public EntityManager produce(){
        return factory.createEntityManager();
    }

    @Override
    public void close() throws IOException {
        factory.close();
    }
}
