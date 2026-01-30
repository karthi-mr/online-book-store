package com.preflearn.obs.book;

import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    public static Specification<Book> searchBookByQuery(String searchQuery) {
        return (root, query, criteriaBuilder) -> {
            String pattern = "%" + searchQuery.toLowerCase() + "%";

            return criteriaBuilder.and(
                  criteriaBuilder.isTrue(root.get("isActive")),
                  criteriaBuilder.or(
                          criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), pattern),
                          criteriaBuilder.like(criteriaBuilder.lower(root.get("author")), pattern),
                          criteriaBuilder.like(criteriaBuilder.lower(root.get("isbn")), pattern)
                  )
            );
        };
    }
}
