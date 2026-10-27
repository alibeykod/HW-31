package ir.maktabsharif.repository.impl;

import ir.maktabsharif.convertor.Convertor;
import ir.maktabsharif.model.dao.ProjectDAO;
import ir.maktabsharif.model.dao.TaskDAO;
import ir.maktabsharif.model.dto.request.ProjectRequestDTO;
import ir.maktabsharif.model.dto.response.TaskResponseDTO;
import ir.maktabsharif.repository.BaseRepositoryImpl;
import ir.maktabsharif.util.HibernateUtil;

import java.util.List;

public class ProjectRepositoryImpl extends BaseRepositoryImpl<ProjectDAO> {

    public ProjectRepositoryImpl(Class<ProjectDAO> entityClass) {
        super(entityClass);
    }

    public List<ProjectDAO> findByManagerId(Long id){
        return HibernateUtil.read(entityManager -> {
            return entityManager.createQuery("select p from ProjectDAO p where p.managerId = :id" , ProjectDAO.class)
                     .setParameter("id" , id)
                     .getResultList();
        });

    }

    public String findProjectNameWithProjectId(Long id){
        return HibernateUtil.read(entityManager -> {
            ProjectDAO found = entityManager.find(ProjectDAO.class , id);
            if (found == null){
                System.out.println("Didn't Found The Project With this Id => " + id);
                return null;
            }
            return found.getName();
        });
    }


}
