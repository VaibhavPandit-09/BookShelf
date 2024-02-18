package routers;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


@WebServlet(name = "DashboardRouter", urlPatterns = {"/Dashboard"})
public class DashboardRouter extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Adjust the path to match the JSP location within the webapp directory
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/Dashboard.jsp");
        dispatcher.forward(request, response);
    }
}
