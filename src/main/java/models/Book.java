package models;

public class Book {
    private String title;
    private String author;
    private int publicationYear;
    private int userId;
    private int bookId;

    // Constructor
    public Book(String title, String author, int publicationYear, int userId) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.userId = userId;
    }

     // Overloaded Constructor with bookId for fetched books
     public Book(int bookId, String title, String author, int publicationYear, int userId) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.userId = userId;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public int getUserId() {
        return userId;
    }
    public int getBookId() {
        return bookId;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    // Optionally, override toString() for easy printing of book details
    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationYear=" + publicationYear +
                ", userId=" + userId +
                '}';
    }
}
