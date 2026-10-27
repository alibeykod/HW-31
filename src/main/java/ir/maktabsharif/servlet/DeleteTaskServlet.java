package ir.maktabsharif.servlet;

import ir.maktabsharif.exception.Business;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "deleteTask" , value = "/tasks/delete")
public class DeleteTaskServlet extends AppContext {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParameter = req.getParameter("id");

        try {
            Long id = Long.parseLong(idParameter);
            validatorTask.delete(id);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid task id");
            return;
        } catch (Business e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Task could not be deleted");
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/tasks");
    }
}
