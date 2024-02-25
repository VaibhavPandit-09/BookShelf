package manager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DbLoader;
import models.Book;

public class BookManager {

public static List<Book> getBooksForUser(Integer userId) {
    List<Book> books = new ArrayList<>();
    String sql = "{call SP_GET_BOOKS_BY_USER(?)}"; // Assuming this is your stored procedure call

    try (Connection conn = DbLoader.getConnection(); // Ensure DbLoader.getConnection() is your method to get a DB connection
         CallableStatement cstmt = conn.prepareCall(sql)) {
        
        cstmt.setInt(1, userId); // Set the parameter for the stored procedure. Index should be 1 if it's the first parameter
        
        try (ResultSet rs = cstmt.executeQuery()) { // Execute the query and get the result set
            while (rs.next()) { // Iterate through the result set and populate the list of books
                String title = rs.getString("title"); // Assuming your result set has a 'title' column
                String author = rs.getString("author"); // Assuming your result set has an 'author' column
                int publicationYear = rs.getInt("publicationYear");
                int bookId = rs.getInt("bookID"); // Adjust the column name as needed
                // Since userId is already known, it can be passed directly to the constructor
                books.add(new Book(bookId,title, author, publicationYear, userId));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Proper exception handling should be implemented
    }

    System.out.println("Retrieved books: " + books.size());

    return books; // Return the list of books
}


    public static boolean addBook(Book book) {
       String sql = "{call SP_ADD_NEW_BOOK(?, ?, ?, ?)}";

    try (Connection conn = DbLoader.getConnection();
         CallableStatement cstmt = conn.prepareCall(sql)) {
        
        // Set parameters for the stored procedure
        cstmt.setString(1, book.getTitle());
        cstmt.setString(2, book.getAuthor());
        cstmt.setInt(3, book.getPublicationYear());
        cstmt.setInt(4,book.getUserId() );
        
        
        boolean result = cstmt.executeUpdate() > 0;
        return result;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
    }

    public static boolean deleteBook(int bookId) {
        String sql = "DELETE FROM Books WHERE bookID =?"; // Direct SQL DELETE statement

        try (Connection conn = DbLoader.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, bookId); // Set the book ID as parameter
            
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
