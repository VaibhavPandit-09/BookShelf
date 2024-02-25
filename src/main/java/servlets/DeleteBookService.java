package servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import manager.BookManager;

@WebServlet(name = "DeleteBook", urlPatterns = "/DeleteBook")
public class DeleteBookService extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extract book ID from request
        int bookId = Integer.parseInt(request.getParameter("id"));
        
        
        // Delete book from the database
        boolean success = BookManager.deleteBook(bookId);
        
        // Set response type and return success status in JSON format
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"success\":" + success + "}");
    }
}
