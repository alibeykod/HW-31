package ir.maktabsharif.repository.impl;

import ir.maktabsharif.model.dao.UserDAO;
import ir.maktabsharif.repository.BaseRepositoryImpl;
import ir.maktabsharif.util.HibernateUtil;

public class UserRepositoryImpl extends BaseRepositoryImpl<UserDAO> {
    public UserRepositoryImpl(Class<UserDAO> entityClass) {
        super(entityClass);
    }
    public String findAssigneeNameWithTaskId(Long id){
        return HibernateUtil.read(entityManager -> {
            UserDAO found = entityManager.find(UserDAO.class , id);
            if (found == null){
                System.out.println("Did Not Find User By This Id => [" + id + "]");
                return null;
            }
            return found.getName();
        });
    }
}
