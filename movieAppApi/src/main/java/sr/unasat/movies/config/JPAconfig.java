package sr.unasat.movies.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAconfig {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    public static EntityManagerFactory getEntityMangerFactory(){
        if (entityManagerFactory == null ) {
            entityManagerFactory = Persistence.createEntityManagerFactory("local-moviespace");
        }
        return entityManagerFactory;
    }

    public static EntityManager getEntityManger(){
        if (entityManager == null || !entityManager.isOpen()) {
            entityManager = getEntityMangerFactory().createEntityManager();
        }
        return entityManager;
    }

    public static void shutdown() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
