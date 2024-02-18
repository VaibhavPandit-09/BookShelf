package models;

// import java.time.LocalDateTime;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
   // private LocalDateTime createdAt;
    private String emailId;

    // Constructor
    public User(int id, String firstName, String lastName, String username, String emailId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        //this.createdAt = createdAt;
        this.emailId = emailId;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    // public LocalDateTime getCreatedAt() {
    //     return createdAt;
    // }

    public String getEmailId() {
        return emailId;
    }

    // toString method for easy printing
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", emailId='" + emailId + '\'' +
                '}';
    }
}
