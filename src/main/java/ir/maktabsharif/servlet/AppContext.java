package ir.maktabsharif.servlet;

import ir.maktabsharif.repository.impl.TaskRepositoryImpl;
import ir.maktabsharif.service.business.validationService.ValidatorTask;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

public class AppContext extends HttpServlet {
    private ValidatorTask validatorTask;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        TaskRepositoryImpl taskRepository = (TaskRepositoryImpl) context.getAttribute("tasRepository");
        this.validatorTask = new ValidatorTask(taskRepository);
    }
}
