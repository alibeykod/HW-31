package ir.maktabsharif.repository.impl;

import ir.maktabsharif.model.dao.UserDAO;
import ir.maktabsharif.repository.BaseRepositoryImpl;

public class UserRepositoryImpl extends BaseRepositoryImpl<UserDAO> {
    public UserRepositoryImpl(Class<UserDAO> entityClass) {
        super(entityClass);
    }
}
