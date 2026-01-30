package com.preflearn.obs.book;

import com.preflearn.obs.book.dto.BookRequest;
import com.preflearn.obs.book.dto.BookResponse;
import com.preflearn.obs.category.Category;
import com.preflearn.obs.common.PageResponse;
import com.preflearn.obs.user.Role;
import com.preflearn.obs.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookResponse findBookById(Long bookId) {
        return this.bookRepository.findById(bookId)
                .map(this.bookMapper::toBookResponse)
                .orElseThrow();
    }

    public PageResponse<BookResponse> findAllBooks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> books = this.bookRepository.findAll(pageable);
        List<BookResponse> bookResponses = books.stream()
                .map(this.bookMapper::toBookResponse)
                .toList();
        return new PageResponse<>(
                bookResponses,
                books.getNumber(),
                books.getSize(),
                books.getTotalElements(),
                books.getTotalPages(),
                books.isFirst(),
                books.isLast()
        );
    }

    public PageResponse<BookResponse> findAllActiveBooks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> books = this.bookRepository.findAllActiveBooks(pageable);
        List<BookResponse> bookResponses = books.stream()
                .map(this.bookMapper::toBookResponse)
                .toList();
        return new PageResponse<>(
                bookResponses,
                books.getNumber(),
                books.getSize(),
                books.getTotalElements(),
                books.getTotalPages(),
                books.isFirst(),
                books.isLast()
        );
    }

    public BookResponse createBook(BookRequest bookRequest) {
        Book book = this.bookMapper.toBook(bookRequest);
        Book savedBook = this.bookRepository.save(book);
        return bookMapper.toBookResponse(savedBook);
    }

    public BookResponse updateBook(Long bookId, BookRequest bookRequest) {
        Book book = this.bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        book.setTitle(bookRequest.title());
        book.setAuthor(bookRequest.author());
        book.setIsbn(bookRequest.isbn());
        book.setDescription(bookRequest.description());
        book.setPrice(bookRequest.price());
        book.setStockQuantity(bookRequest.stockQuantity());
        book.setCategory(Category.builder().id(bookRequest.categoryId()).build());
        Book savedBook = this.bookRepository.save(book);
        return bookMapper.toBookResponse(savedBook);
    }

    public void deleteBook(Long bookId) {
        this.bookRepository.deleteById(bookId);
    }

    public void modifyBookActiveStatus(Long bookId) {
        Book book = this.bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        book.setActive(!book.isActive());
        this.bookRepository.save(book);
    }

    public PageResponse<BookResponse> findAllBooksByCategory(Long categoryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> books = this.bookRepository.findAllBooksByCategory(pageable, categoryId);
        List<BookResponse> bookResponses = books.stream()
                .map(this.bookMapper::toBookResponse)
                .toList();
        return new PageResponse<>(
                bookResponses,
                books.getNumber(),
                books.getSize(),
                books.getTotalElements(),
                books.getTotalPages(),
                books.isFirst(),
                books.isLast()
        );
    }

    public PageResponse<BookResponse> searchBooks(int page, int size, String query, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();

        Pageable pageable = PageRequest.of(page, size);
        if (query == null || query.trim().isEmpty()) {
            if (user == null || user.getRole() == Role.ADMIN) {
                return this.findAllBooks(page, size);
            } else {
                return this.findAllActiveBooks(page, size);
            }
        }
        Page<Book> books = this.bookRepository.findAll(BookSpecification.searchBookByQuery(query), pageable);
        List<BookResponse> bookResponses = books.stream()
                .map(this.bookMapper::toBookResponse)
                .toList();
        return new PageResponse<>(
                bookResponses,
                books.getNumber(),
                books.getSize(),
                books.getTotalElements(),
                books.getTotalPages(),
                books.isFirst(),
                books.isLast()
        );
    }

    public void updateStockQuantity(Long bookId, int quantity) {
        Book book = this.bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        book.setStockQuantity(quantity);
        this.bookRepository.save(book);
    }
}
