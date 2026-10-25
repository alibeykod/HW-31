package ir.maktabsharif.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.function.Consumer;
import java.util.function.Function;

public class HibernateUtil {
    private static final String PERSISTENCE_UNIT = "postgres-pu";
    private static EntityManagerFactory emf;

    public static synchronized EntityManagerFactory getEMF() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        }
        return emf;
    }

    public static synchronized EntityManager getEM() {
        return getEMF().createEntityManager();
    }

    public static <T> T read(Function<EntityManager, T> operation) {
        EntityManager em = getEM();
        EntityTransaction tx = em.getTransaction();
        try {
            return operation.apply(em);
        } finally {
            em.close();
        }
    }

    public static void inTx(Consumer<EntityManager> operation) {
        EntityManager em = getEM();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            operation.accept(em);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public static <T> T inTxReturn(Function<EntityManager, T> operation) {
        EntityManager em = getEM();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            T result = operation.apply(em);
            tx.commit();
            return result;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}
