package com.preflearn.obs.book;

import com.preflearn.obs.book.dto.BookRequest;
import com.preflearn.obs.book.dto.BookResponse;
import com.preflearn.obs.common.PageResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("books")
@Tag(
        name = "Books",
        description = "Manage books"
)
public class BookController {

    private final BookService bookService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PageResponse<BookResponse>> findAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {
        return ok(this.bookService.findAllBooks(page, size));
    }

    @GetMapping("/active")
    public ResponseEntity<PageResponse<BookResponse>> findAllActiveBooks(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {
        return ok(this.bookService.findAllActiveBooks(page, size));
    }

    @GetMapping("/category/{category-id}")
    public ResponseEntity<PageResponse<BookResponse>> findAllBooksByCategory(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            @PathVariable(name = "category-id") Long categoryId
    ) {
        return ok(this.bookService.findAllBooksByCategory(categoryId, page, size));
    }

    @GetMapping("/search")
    public ResponseEntity<PageResponse<BookResponse>> searchBooks(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            @RequestParam(name = "query") String query,
            Authentication connectedUser
    ) {
        return ok(this.bookService.searchBooks(page, size, query, connectedUser));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BookResponse> createBook(@RequestBody @Valid BookRequest bookRequest) {
        return status(CREATED)
                .body(this.bookService.createBook(bookRequest));
    }

    @PutMapping("{book-id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BookResponse> updateBook(
            @PathVariable(name = "book-id") Long categoryId,
            @RequestBody @Valid BookRequest bookRequest
    ) {
        return ok(this.bookService.updateBook(categoryId, bookRequest));
    }

    @GetMapping("{book-id}")
    public ResponseEntity<BookResponse> findBookById(
            @PathVariable(name = "book-id") Long categoryId
    ) {
        return ok(this.bookService.findBookById(categoryId));
    }

    @DeleteMapping("{book-id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteBook(@PathVariable(name = "book-id") Long categoryId) {
        this.bookService.deleteBook(categoryId);
        return noContent().build();
    }

    @PatchMapping("update-active-status/{book-id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> modifyBookActiveStatus(@PathVariable(name = "book-id") Long bookId) {
        this.bookService.modifyBookActiveStatus(bookId);
        return noContent().build();
    }

    @PatchMapping("update-stock/{book-id}")
    public ResponseEntity<Void> updateStockQuantity(
            @PathVariable(name = "book-id") Long bookId,
            @RequestParam(name = "stock") int stock
    ) {
        this.bookService.updateStockQuantity(bookId, stock);
        return noContent().build();
    }

    @PostMapping(value = "{book-id}/cover", consumes = "multipart/form-data")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> uploadBookCover(
            @PathVariable("book-id") Long bookId,
            @Parameter() @RequestPart("file") MultipartFile file,
            Authentication authentication
    ) {
        this.bookService.uploadBookCover(file, authentication, bookId);
        return noContent().build();
    }
}
