package ir.maktabsharif.servlet;

import ir.maktabsharif.model.dto.response.UserResponseDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "userServlet", value = "/users")
public class UserServlet extends AppContext {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        List<UserResponseDTO> users = validatorUser.findAll();

        out.println("""
                <!DOCTYPE html>
                <html>
                <head>
                    <meta charset="UTF-8">
                    <title>Users List</title>
                    <style>
                        table, th, td {
                            border: 1px solid black;
                            border-collapse: collapse;
                            padding: 8px;
                        }
                    </style>
                </head>
                <body>
                <h2>Users List</h2>
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Email</th>
                    </tr>
                """);

        for (UserResponseDTO user : users) {
            out.println("<tr>");
            out.println("<td>" + user.getId() + "</td>");
            out.println("<td>" + user.getName() + "</td>");
            out.println("<td>" + user.getEmail() + "</td>");
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
