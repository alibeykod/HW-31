package ir.maktabsharif.repository;

import ir.maktabsharif.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public abstract class BaseRepositoryImpl <T> implements BaseRepository<T>{
    private final Class<T> entityClass;

    public BaseRepositoryImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    @Override
    public void add(T t) {
        HibernateUtil.inTx(entityManager -> entityManager.persist(t));
    }

    @Override
    public T update(T t) {
       return HibernateUtil.inTxReturn(entityManager -> entityManager.merge(t));
    }

    @Override
    public Optional<T> findById(Long id) {
        return HibernateUtil.read(entityManager -> {
            T t = entityManager.find(entityClass , id);
            return Optional.ofNullable(t);
        });
    }

        @Override
        public List<T> findAll() {
            return HibernateUtil.read(entityManager -> {
                String jpql = "SELECT t FROM " + entityClass.getSimpleName() + " t";
                return entityManager.createQuery(jpql , entityClass).getResultList();
            });
        }

        @Override
        public boolean delete(Long id) {
            return HibernateUtil.inTxReturn(entityManager -> {
                T t = entityManager.find(entityClass , id);
                if (t != null){
                    entityManager.remove(t);
                    return true;
                }else {
                    System.out.println("Object Not Found And Remove !!!");
                    return false;
                }
            });
        }
}
