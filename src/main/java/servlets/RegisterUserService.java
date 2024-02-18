package servlets;

import manager.UserManager;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddUser", urlPatterns = {"/AddUser"})
public class RegisterUserService extends HttpServlet {
    private UserManager userManager;

    @Override
    public void init() {
        userManager = new UserManager(); // Initialize your UserManager
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        

        // Assuming UserManager has an addUser method
        boolean success = userManager.addUser(firstName, lastName, username, email, password);

        if (success) {
            response.sendRedirect("/jsp/Login.jsp"); // Redirect or display success message
        } else {
            request.setAttribute("errorMessage", "User creation failed.");
            request.getRequestDispatcher("/jsp/Register.jsp").forward(request, response);
        }
    }
}