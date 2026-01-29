package com.preflearn.obs.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(
            """
            SELECT
                category
            FROM
                Category category
            WHERE
                category.isActive = true
            """
    )
    List<Category> findAllActiveCategories();
}
