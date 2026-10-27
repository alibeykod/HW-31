package ir.maktabsharif.servlet;

import ir.maktabsharif.exception.Business;
import ir.maktabsharif.model.dto.request.TaskRequestDTO;
import ir.maktabsharif.model.dto.response.ProjectResponseDTO;
import ir.maktabsharif.model.dto.response.TaskResponseDTO;
import ir.maktabsharif.model.dto.response.UserResponseDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "updateTask", value = "/tasks/update")
public class UpdateTaskServlet extends AppContext {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("""
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <title>Add Task</title>
            </head>
            <body>
            <h2>Add New Task</h2>
            <form action="%s/tasks/add" method="post">
                Title: <input type="text" name="title"><br>
                Estimated Minutes: <input type="number" name="estimatedMinutes"><br>
                Assignee Id: <input type="number" name="assigneeId"><br>
                Project Id: <input type="number" name="projectId"><br>
                <button type="submit">Add</button>
            </form>
            <a href="%s/tasks">Back</a>
            </body></html>
            """.formatted(req.getContextPath(), req.getContextPath()));
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TaskRequestDTO dto = new TaskRequestDTO();
        dto.setTitle(req.getParameter("title"));
        dto.setEstimatedMinutes(Integer.parseInt(req.getParameter("estimatedMinutes")));
        dto.setAssigneeId(Long.parseLong(req.getParameter("assigneeId")));
        dto.setProjectId(Long.parseLong(req.getParameter("projectId")));

        try {
            validatorTask.update(dto, Long.parseLong(req.getParameter("id")));
        } catch (Business e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        resp.sendRedirect(req.getContextPath() + "/tasks");
    }
}
