package com.preflearn.obs.category;

import com.preflearn.obs.category.dto.CategoryRequest;
import com.preflearn.obs.category.dto.CategoryResponse;
import com.preflearn.obs.common.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryResponse> findAllActiveCategories() {
        List<Category> categories = this.categoryRepository.findAllActiveCategories();
        return categories.stream()
                .map(this.categoryMapper::toCategoryResponse)
                .collect(Collectors.toList());
    }

    public PageResponse<CategoryResponse> findAllCategories(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").ascending());
        Page<Category> categories = this.categoryRepository.findAll(pageable);
        List<CategoryResponse> categoryResponses =  categories.stream()
                .map(this.categoryMapper::toCategoryResponse)
                .toList();
        return new PageResponse<>(
                categoryResponses,
                categories.getNumber(),
                categories.getSize(),
                categories.getTotalElements(),
                categories.getTotalPages(),
                categories.isFirst(),
                categories.isLast()
        );
    }

    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        Category category = Category.builder()
                .name(categoryRequest.name())
                .isActive(true)
                .build();
        Category savedCategory = this.categoryRepository.save(category);
        return this.categoryMapper.toCategoryResponse(savedCategory);
    }

    public CategoryResponse updateCategory(Long categoryId, CategoryRequest categoryRequest) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        category.setName(categoryRequest.name());
        Category savedCategory = this.categoryRepository.save(category);
        return this.categoryMapper.toCategoryResponse(savedCategory);
    }

    public CategoryResponse findCategoryById(Long categoryId) {
        return this.categoryRepository.findById(categoryId)
                .map(this.categoryMapper::toCategoryResponse)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
    }

    public void deleteCategoryById(Long categoryId) {
        this.categoryRepository.deleteById(categoryId);
    }

    public void UpdateCategoryStatus(Long categoryId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        category.setActive(!category.isActive());
        this.categoryRepository.save(category);
    }
}
