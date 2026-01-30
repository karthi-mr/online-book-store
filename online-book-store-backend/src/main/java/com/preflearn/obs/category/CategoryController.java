package com.preflearn.obs.category;

import com.preflearn.obs.category.dto.CategoryRequest;
import com.preflearn.obs.category.dto.CategoryResponse;
import com.preflearn.obs.common.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("categories")
@RequiredArgsConstructor
@Tag(
        name = "Categories",
        description = "Book Categories"
)
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/active-category")
    public ResponseEntity<List<CategoryResponse>> findAllActiveCategories() {
        return ok(this.categoryService.findAllActiveCategories());
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PageResponse<CategoryResponse>> findAllCategories(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {
        return ok(this.categoryService.findAllCategories(page, size));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest) {
        return status(CREATED)
                .body(this.categoryService.createCategory(categoryRequest));
    }

    @GetMapping("{category-id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CategoryResponse> findCategoryById(
            @PathVariable("category-id") Long categoryId
    ) {
        return ok(this.categoryService.findCategoryById(categoryId));
    }

    @PutMapping("{category-id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CategoryResponse> updateCategory(
            @PathVariable("category-id") Long categoryId,
            @RequestBody CategoryRequest categoryRequest
    ) {
        return ok(this.categoryService.updateCategory(categoryId, categoryRequest));
    }

    @DeleteMapping("{category-id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteCategory(
        @PathVariable("category-id") Long categoryId
    ) {
        this.categoryService.deleteCategoryById(categoryId);
        return status(NO_CONTENT)
                .build();
    }

    @PatchMapping("update-active-status/{category-id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> UpdateCategoryStatus(
        @PathVariable("category-id") Long categoryId
    ) {
        this.categoryService.UpdateCategoryStatus(categoryId);
        return status(NO_CONTENT)
                .build();
    }
}


















