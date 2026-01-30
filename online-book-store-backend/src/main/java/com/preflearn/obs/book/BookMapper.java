package com.preflearn.obs.book;

import com.preflearn.obs.book.dto.BookRequest;
import com.preflearn.obs.book.dto.BookResponse;
import com.preflearn.obs.category.Category;
import com.preflearn.obs.category.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookMapper {

    private final CategoryMapper categoryMapper;

    public BookResponse toBookResponse(Book book) {
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getDescription(),
                book.getPrice(),
                book.getStockQuantity(),
                book.getImageUrl(),
                book.isActive(),
                book.getCreatedAt(),
                book.getLastModifiedAt(),
                this.categoryMapper.toCategoryResponse(book.getCategory())
        );
    }

    public Book toBook(BookRequest bookRequest) {
        return Book.builder()
                .title(bookRequest.title())
                .author(bookRequest.author())
                .isbn(bookRequest.isbn())
                .description(bookRequest.description())
                .price(bookRequest.price())
                .stockQuantity(bookRequest.stockQuantity())
                .isActive(true)
                .category(Category.builder().id(bookRequest.categoryId()).build())
                .build();
    }
}
