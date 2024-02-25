<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Book" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <style>
        body {
            padding: 20px;
            background-color: #f7f7f7;
        }
        .top-bar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px;
    background-color: #ffffff;
    box-shadow: 0 2px 4px rgba(0,0,0,.1);
    margin-bottom: 20px;
    border-radius: 5px;
}

.button-group {
    display: flex;
    align-items: center;
    gap: 10px; /* Adds space between the buttons */
}

       
        .book-card {
            background-color: #ffffff;
            margin-bottom: 20px;
            box-shadow: 0 2px 4px rgba(0,0,0,.1);
            border-radius: 5px;
            transition: transform .2s;
        }
        .book-card:hover {
            transform: scale(1.02);
        }
        .card-body {
            position: relative;
        }
        .delete-btn {
            position: absolute;
            top: 10px;
            right: 10px;
            cursor: pointer;
        }
    </style>
</head>
<body>

    <div class="top-bar">
        <div>User: <strong>${FirstName} ${LastName}</strong></div>
        <div class="button-group">
            <button class="btn btn-primary" data-toggle="modal" data-target="#addBookModal">Add Book</button>
            <!-- Logout Button -->
            <a href="Logout" class="btn btn-danger">Logout</a>
        </div>
    </div>
    
    

<div class="container">
    <c:choose>
        <c:when test="${not empty Books}">
            <c:forEach items="${Books}" var="book">
                <div class="card book-card">
                    <div class="card-body">
                        <h5 class="card-title">${book.title}</h5>
                        <h6 class="card-subtitle mb-2 text-muted">${book.author}</h6>
                        <p class="card-text">Published Year: ${book.publicationYear}</p>
                        <button class="btn btn-danger delete-btn" onclick="deleteBook('${book.bookId}')">Delete</button>
                    </div>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <div class="alert alert-info">No books found for the user.</div>
        </c:otherwise>
    </c:choose>
</div>

<div class="modal fade" id="addBookModal" tabindex="-1" role="dialog" aria-labelledby="addBookModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addBookModalLabel">Add New Book</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="addBookForm">
                    <div class="form-group">
                        <label for="bookTitle">Title</label>
                        <input type="text" class="form-control" id="bookTitle" name="title" required>
                    </div>
                    <div class="form-group">
                        <label for="bookAuthor">Author</label>
                        <input type="text" class="form-control" id="bookAuthor" name="author" required>
                    </div>
                    <div class="form-group">
                        <label for="bookYear">Publication Year</label>
                        <input type="number" class="form-control" id="bookYear" name="publicationYear" required>
                    </div>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Add Book</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
$(document).ready(function() {
    $('#addBookForm').on('submit', function(e) {
        e.preventDefault();
        var formData = $(this).serialize();
        $.ajax({
            type: 'POST',
            url: '/bookshelf/AddBook',
            data: formData,
            success: function(response) {
                $('#addBookModal').modal('hide');
                location.reload();
            },
            error: function() {
                alert('Error adding book. Please try again.');
            }
        });
    });
});

function deleteBook(bookId) {
    if (confirm('Are you sure you want to delete this book?')) {
        $.ajax({
            type: 'POST',
            url: '/bookshelf/DeleteBook', // Adjust based on your servlet mapping
            data: { 
                id: bookId
            },
            success: function(response) {
                alert('Book deleted successfully.');
                location.reload(); // Reload to reflect changes
            },
            error: function() {
                alert('Error deleting book. Please try again.');
            }
        });
    }
}

</script>

</body>
</html>
