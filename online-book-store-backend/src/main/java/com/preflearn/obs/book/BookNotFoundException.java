package com.preflearn.obs.book;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(Long bookId) {
        super("Book with id " + bookId + " not found");
    }
}
