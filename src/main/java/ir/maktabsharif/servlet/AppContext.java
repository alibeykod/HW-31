package ir.maktabsharif.servlet;

import ir.maktabsharif.repository.impl.ProjectRepositoryImpl;
import ir.maktabsharif.repository.impl.TaskRepositoryImpl;
import ir.maktabsharif.repository.impl.UserRepositoryImpl;
import ir.maktabsharif.service.business.validationService.ValidatorProject;
import ir.maktabsharif.service.business.validationService.ValidatorTask;
import ir.maktabsharif.service.business.validationService.ValidatorUser;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

public abstract class AppContext extends HttpServlet {
        protected TaskRepositoryImpl taskRepository;
        protected UserRepositoryImpl userRepository;
        protected ProjectRepositoryImpl projectRepository;

        protected ValidatorTask validatorTask;
        protected ValidatorUser validatorUser;
        protected ValidatorProject validatorProject;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        ServletContext context = config.getServletContext();

        this.taskRepository = (TaskRepositoryImpl) context.getAttribute("taskRepository");
        this.userRepository = (UserRepositoryImpl) context.getAttribute("userRepository");
        this.projectRepository = (ProjectRepositoryImpl) context.getAttribute("projectRepository");

        this.validatorTask = new ValidatorTask(taskRepository);
        this.validatorUser = new ValidatorUser(userRepository);
        this.validatorProject = new ValidatorProject(projectRepository);
    }
}
