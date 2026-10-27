package ir.maktabsharif.servlet;

import ir.maktabsharif.model.dto.response.TaskResponseDTO;
import ir.maktabsharif.service.auxiliaryServices.sessionService.Session;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "tasks", value = "/tasks")
public class TaskServlet extends AppContext {
    Session sessionService = new Session();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Integer visitCount = sessionService.incrementVisitCount(session);

        PrintWriter out = resp.getWriter();
        String assigneeIdParam = req.getParameter("assigneeId");
        List<TaskResponseDTO> tasks;

        if (assigneeIdParam != null && !assigneeIdParam.trim().isEmpty()) {
            try {
                Long assigneeId = Long.parseLong(assigneeIdParam.trim());
                tasks = validatorTask.findByAssigneeId(assigneeId);
            } catch (NumberFormatException e) {
                tasks = validatorTask.findAll();
            }
        } else {
            tasks = validatorTask.findAll();
        }


        out.println("""
                <!DOCTYPE html>
                <html>
                <head>
                <style>
                    table, th, td {
                        border: 1px solid black;
                    }
                </style>
                </head>
                <body>
                <p>you have Visited this page :<strong>[
                """);
        out.println(visitCount);
        out.println("]</strong> <br> Task Size : [" + tasks.size() + "]");
        out.println("""
                </p><br><hr>
                <h2>Tasks :</h2>
                <table style="width:100%">
                <tr>
                    <th>Title</th>  
                    <th>Estimated Minutes</th>  
                    <th>Assignee Name</th>  
                    <th>Project Name</th>
                    <th>Edit</th>
                    <th>Delete</th>
       
                </tr>  
                
                """);
        for (TaskResponseDTO taskResponseDTO : tasks) {
            out.println("<tr> <td>" + taskResponseDTO.getTitle() + "</td>");
            out.println("<td>" + taskResponseDTO.getEstimatedMinutes() + "</td>");

            String assigneeName = validatorUser.findAssigneeNameWithTaskId(taskResponseDTO.getAssigneeId());
            out.println("<td>" + (assigneeName != null ? assigneeName : "Unassigned") + "</td>");

            String projectName = validatorProject.findProjectNameWithProjectId(taskResponseDTO.getProjectId());
            out.println("<td>" + (projectName != null ? projectName : "No Project") + "</td>");


            out.println("<td><form action=\"/tasks/update\" method=\"get\" style=\"display:inline;\">");
            out.println("<input type=\"hidden\" name=\"id\" value=\"" + taskResponseDTO.getId() + "\">");
            out.println("<button type=\"submit\">Edit</button>");
            out.println("</form> " +"</td>" + "<td>");

            out.println("<form action=\"/tasks/delete\" method=\"post\" style=\"display:inline; margin-left: 5px;\">");
            out.println("<input type=\"hidden\" name=\"id\" value=\"" + taskResponseDTO.getId() + "\">");
            out.println("<button type=\"submit\" onclick=\"return confirm('Are you sure?');\">Delete</button>");
            out.println("</form> </td>");

            out.println("</tr> ");
        }
        out.println("</table>");

        out.println("""
                </body>
                </html>
                """);

    }
}
