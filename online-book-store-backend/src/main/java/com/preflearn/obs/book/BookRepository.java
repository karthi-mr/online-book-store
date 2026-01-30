package com.preflearn.obs.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    @Query(
            """
            SELECT
                book
            FROM
                Book book
            WHERE
                book.isActive = true
            """
    )
    Page<Book> findAllActiveBooks(Pageable pageable);

    @Query(
            """
            SELECT
                book
            FROM
                Book book
            WHERE
                book.isActive = TRUE AND
                book.category.id = :categoryId
            """
    )
    Page<Book> findAllBooksByCategory(Pageable pageable, Long categoryId);

    @Query(
            """
            SELECT
                book
            FROM
                Book book
            WHERE
                book.isActive = TRUE AND
                (
                    book.title ILIKE CONCAT('%', :query, '%') OR
                    book.author ILIKE CONCAT('%', :query, '%') OR
                    book.isbn ILIKE CONCAT('%', :query, '%')
                )
            """
    )
    Page<Book> searchBooks(Pageable pageable, @Param("query") String query);
}
