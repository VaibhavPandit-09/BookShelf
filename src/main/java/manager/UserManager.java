package manager;

import java.sql.CallableStatement;
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DbLoader;
import models.User;
import utility.HashingUtility;

public class UserManager {

    public User getUserById(int userId) {
        User user = null;
        String query = "SELECT id, firstName, lastName, email_id FROM users WHERE id = ?";

        try (Connection conn = DbLoader.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, userId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    String username = rs.getString("username");
                    String emailId = rs.getString("email_id");

                    user = new User(id, firstName, lastName, username ,emailId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

   public boolean addUser(String firstName, String lastName, String username,  String email, String password) {
    // Hash the password
    String hashedPassword = HashingUtility.hashPassword(password);


    // Call the stored procedure
    String sql = "{call SP_REGISTER_USER(?, ?, ?, ?, ?)}";

    try (Connection conn = DbLoader.getConnection();
         CallableStatement cstmt = conn.prepareCall(sql)) {
        
        // Set parameters for the stored procedure
        cstmt.setString(1, firstName);
        cstmt.setString(2, lastName);
        cstmt.setString(3, email);
        cstmt.setString(4, username);
        cstmt.setString(5, hashedPassword); // Pass the hashed password
        
        boolean result = cstmt.executeUpdate() > 0;
        return result;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


}
