package servlets;

import java.io.IOException;
//import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DbLoader;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utility.HashingUtility;

@WebServlet(name = "LoginUser", urlPatterns = "/LoginUser")
public class LoginService extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
    
        try (Connection conn = DbLoader.getConnection()) {
            String sql = "SELECT passwordHash FROM UserLogin WHERE username = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, username);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        String storedHash = rs.getString("passwordHash");
                        if (HashingUtility.checkPassword(password, storedHash)) {
                            // Login successful, manage session
                            HttpSession session = request.getSession();
                            session.setAttribute("user", username); // Store username in session
                            int userId = getUserId(conn, username);
                            session.setAttribute("userId", userId);
                            response.sendRedirect("/bookshelf/Dashboard"); // Adjust the redirection as needed
                        } else {
                            // Login failed, redirect or forward to login page with an error message
                            request.setAttribute("errorMessage", "Invalid username or password");
                            request.getRequestDispatcher("/jsp/Login.jsp").forward(request, response);
                        }
                    } else {
                        // Username not found
                        request.setAttribute("errorMessage", "Invalid username or password");
                        request.getRequestDispatcher("/jsp/Login.jsp").forward(request, response);
                    }
                }
            }
        } catch (SQLException e) {
            throw new ServletException("Database connection problem.", e);
        } catch (Exception e) {
            throw new ServletException("Hashing problem.", e);
        }
    }

    private int getUserId(Connection conn, String username) {
        int userId = -1;
        String sql = "SELECT userID FROM UserLogin WHERE username = ?";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                        userId = rs.getInt(1);
                } 
        } catch (Exception e) {
            e.printStackTrace();
        }
         } catch (Exception e){
            e.printStackTrace();
        }
        return userId;
    }
}