package ir.maktabsharif.servlet;

import ir.maktabsharif.exception.Business;
import ir.maktabsharif.model.dao.ProjectDAO;
import ir.maktabsharif.model.dao.Role;
import ir.maktabsharif.model.dao.TaskDAO;
import ir.maktabsharif.model.dao.UserDAO;
import ir.maktabsharif.model.dto.request.ProjectRequestDTO;
import ir.maktabsharif.model.dto.request.TaskRequestDTO;
import ir.maktabsharif.model.dto.request.UserRequestDTO;
import ir.maktabsharif.model.dto.response.ProjectResponseDTO;
import ir.maktabsharif.model.dto.response.UserResponseDTO;
import ir.maktabsharif.repository.impl.ProjectRepositoryImpl;
import ir.maktabsharif.repository.impl.TaskRepositoryImpl;
import ir.maktabsharif.repository.impl.UserRepositoryImpl;
import ir.maktabsharif.service.business.validationService.ValidatorProject;
import ir.maktabsharif.service.business.validationService.ValidatorTask;
import ir.maktabsharif.service.business.validationService.ValidatorUser;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        TaskRepositoryImpl taskRepository = new TaskRepositoryImpl(TaskDAO.class);
        UserRepositoryImpl userRepository = new UserRepositoryImpl(UserDAO.class);
        ProjectRepositoryImpl projectRepository = new ProjectRepositoryImpl(ProjectDAO.class);

        context.setAttribute("taskRepository", taskRepository);
        context.setAttribute("userRepository", userRepository);
        context.setAttribute("projectRepository", projectRepository);

        ValidatorTask validatorTask = new ValidatorTask(taskRepository);
        ValidatorUser validatorUser = new ValidatorUser(userRepository);
        ValidatorProject validatorProject = new ValidatorProject(projectRepository);


        try {
            if (validatorUser.findAll().isEmpty()) {
                UserResponseDTO manager = validatorUser.add(new UserRequestDTO(Role.MANAGER, "JohnDoe", "John@gmail.com"));
                UserResponseDTO developer = validatorUser.add(new UserRequestDTO(Role.DEVELOPER, "JainDoe", "Jain@gmail.com"));

                ProjectResponseDTO digikala = validatorProject.add(
                        new ProjectRequestDTO(manager.getId(), "Digikala", "Some Description")
                );
                ProjectResponseDTO netflix = validatorProject.add(
                        new ProjectRequestDTO(manager.getId(), "NetFelix", "Some Description X 2")
                );

                validatorTask.add(new TaskRequestDTO("Digikala Task 1", 88, developer.getId(), digikala.getManagerId()));
                validatorTask.add(new TaskRequestDTO("Digikala Task 2", 588, developer.getId(), digikala.getManagerId()));
                validatorTask.add(new TaskRequestDTO("NetFelix Task 1", 1008, developer.getId(), netflix.getManagerId()));

            } else {
                validatorUser.findAll().forEach(System.out::println);
            }
        } catch (Business e) {
            throw new RuntimeException("Failed to initial data in AppInitializer", e);
        }


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Destroying Repository From Application Context Listener ... ");
    }
}
