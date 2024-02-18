package servlets;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
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

        // Assuming you have a method to get DB connection
        try (Connection conn = DbLoader.getConnection()) {
            // Hash the password - Ensure you hash the password in the same way it was hashed when stored
            // This example assumes you're re-hashing and comparing, but you might fetch the hash and compare using BCrypt
            String hashedPassword = HashingUtility.hashPassword(password); // Adjust this line to match your actual hashing
            byte[] hashBytes = hashedPassword.getBytes(java.nio.charset.StandardCharsets.UTF_8);

            String sql = "{call SP_USER_LOGIN(?, ?)}";
            try (CallableStatement cstmt = conn.prepareCall(sql)) {
                cstmt.setString(1, username);
                cstmt.setBytes(2,hashBytes); // Adjust if your hashing utility returns a byte[]

                try (ResultSet rs = cstmt.executeQuery()) {
                    if (rs.next() && "Login successful".equals(rs.getString("Message"))) {
                        // Login successful, manage session
                        HttpSession session = request.getSession();
                        session.setAttribute("user", username); // Store username in session
                        // Redirect or forward to success page
                        response.sendRedirect("/jsp/Dashboard.jsp"); // Adjust the redirection as needed
                    } else {
                        // Login failed, redirect or forward to login page with an error message
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
}
