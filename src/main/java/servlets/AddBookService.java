package servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import manager.BookManager;
import models.Book;

@WebServlet(name = "/AddBook", urlPatterns = "/AddBook")
public class AddBookService extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extract book details from request
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        int publicationYear = Integer.parseInt(request.getParameter("publicationYear"));
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        
        // Add book to the database
        boolean success = BookManager.addBook(new Book(title, author, publicationYear, userId)); // Implement this method
        
        // Set response type and return success status
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"success\":" + success + "}");
    }
}

