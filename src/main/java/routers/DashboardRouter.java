package routers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import manager.BookManager;
import manager.UserManager;
import models.Book;
import jakarta.servlet.annotation.*;
import models.User;



@WebServlet(name = "DashboardRouter", urlPatterns = {"/Dashboard"})
public class DashboardRouter extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        UserManager uManager = new UserManager();
        User user = uManager.getUserById(userId);
        
        // Fetch books from the database for the logged-in user
        List<Book> books = BookManager.getBooksForUser(userId); // Implement this method
        request.setAttribute("FirstName", user.getFirstName());
        request.setAttribute("LastName", user.getLastName());
        request.setAttribute("Books", books);
        System.out.println("Books set in request: " + books.size());
        
        // Forward to JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/Dashboard.jsp");
        dispatcher.forward(request, response);
    }   
}
