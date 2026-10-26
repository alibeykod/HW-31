package ir.maktabsharif.servlet;

import ir.maktabsharif.service.auxiliaryServices.sessionService.Session;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "task" , value = "/task")
public class TaskServlet extends HttpServlet {
    Session sessionService = new Session();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Integer visitCount = sessionService.incrementVisitCount(session);

        PrintWriter out = resp.getWriter();

        out.println("""
                <!DOCTYPE html>
                <html>
                <head>
                    <title>Task</title>
                </head>
                <body>
                <p>you have Visited this page :
                """);
        out.println(visitCount);
        out.println("""
                    </p>
                    </body>
                    </html>
                    """);
    }
}
