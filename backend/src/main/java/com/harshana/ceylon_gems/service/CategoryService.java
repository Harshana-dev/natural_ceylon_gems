package com.harshana.ceylon_gems.service;

import com.harshana.ceylon_gems.dto.request.CategoryCreateRequest;
import com.harshana.ceylon_gems.dto.request.CategoryUpdateRequest;
import com.harshana.ceylon_gems.dto.response.CategoryResponse;
import com.harshana.ceylon_gems.entity.Category;
import com.harshana.ceylon_gems.dto.exception.BadRequestException;
import com.harshana.ceylon_gems.dto.exception.NotFoundException;
import com.harshana.ceylon_gems.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryResponse> list() {
        return categoryRepository.findAll().stream()
                .map(c -> new CategoryResponse(c.getId(), c.getName(), c.getSlug()))
                .toList();
    }

    public CategoryResponse create(CategoryCreateRequest req) {
        String slug = toSlug(req.name());

        if (categoryRepository.existsBySlug(slug)) {
            throw new BadRequestException("Category already exists: " + req.name());
        }

        Category saved = categoryRepository.save(
                Category.builder()
                        .name(req.name().trim())
                        .slug(slug)
                        .build()
        );

        return new CategoryResponse(saved.getId(), saved.getName(), saved.getSlug());
    }

    public CategoryResponse update(Long id, CategoryUpdateRequest req) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found: " + id));

        String newSlug = toSlug(req.name());
        if (!newSlug.equals(category.getSlug()) && categoryRepository.existsBySlug(newSlug)) {
            throw new BadRequestException("Another category already uses this name/slug.");
        }

        category.setName(req.name().trim());
        category.setSlug(newSlug);

        Category saved = categoryRepository.save(category);
        return new CategoryResponse(saved.getId(), saved.getName(), saved.getSlug());
    }

    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new NotFoundException("Category not found: " + id);
        }
        categoryRepository.deleteById(id);
    }

    private String toSlug(String input) {
        String nowhitespace = input.trim().replaceAll("\\s+", "-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = normalized.replaceAll("[^\\w-]", "").toLowerCase(Locale.ROOT);
        return slug.replaceAll("-{2,}", "-");
    }
}