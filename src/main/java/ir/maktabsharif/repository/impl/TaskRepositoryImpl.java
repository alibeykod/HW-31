package ir.maktabsharif.repository.impl;

import ir.maktabsharif.model.dao.TaskDAO;
import ir.maktabsharif.repository.BaseRepositoryImpl;
import ir.maktabsharif.util.HibernateUtil;

import java.util.List;

public class TaskRepositoryImpl extends BaseRepositoryImpl<TaskDAO> {
    public TaskRepositoryImpl(Class<TaskDAO> entityClass) {
        super(entityClass);
    }

    public List<TaskDAO> findByAssignee(Long id){
        return HibernateUtil.read(entityManager -> {
            return entityManager.createQuery("select t from TaskDAO t where t.assigneeId = :id" , TaskDAO.class)
                    .setParameter("id" , id)
                    .getResultList();
        }) ;
    }

    public List<TaskDAO> findByProjectId(Long id){
        return HibernateUtil.read(entityManager -> {
            return entityManager.createQuery("select t from TaskDAO t where t.projectId = :id" , TaskDAO.class)
                    .setParameter("id" , id)
                    .getResultList();
        });
    }
}