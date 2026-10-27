package ir.maktabsharif.servlet;

import ir.maktabsharif.model.dto.response.ProjectResponseDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "projectServlet", value = "/projects")
public class ProjectServlet extends AppContext {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        List<ProjectResponseDTO> projects = validatorProject.findAll();

        out.println("""
                <!DOCTYPE html>
                <html>
                <head>
                    <meta charset="UTF-8">
                    <title>Projects List</title>
                    <style>
                        table, th, td {
                            border: 1px solid black;
                            border-collapse: collapse;
                            padding: 8px;
                        }
                    </style>
                </head>
                <body>
                <h2>Projects List</h2>
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Project Name</th>
                        <th>Description</th>
                    </tr>
                """);

        for (ProjectResponseDTO project : projects) {
            out.println("<tr>");
            out.println("<td>" + project.getId() + "</td>");
            out.println("<td>" + project.getName() + "</td>");
            out.println("<td>" + project.getDescription() + "</td>");
            out.println("</tr>");
        }

        out.println("""
                </table>
                <br>
                <a href="%s/tasks">Back to Tasks</a>
                </body>
                </html>
                """.formatted(req.getContextPath()));
    }
}
