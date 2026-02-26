package com.harshana.ceylon_gems.controller.admin;

import com.harshana.ceylon_gems.dto.request.CategoryCreateRequest;
import com.harshana.ceylon_gems.dto.request.CategoryUpdateRequest;
import com.harshana.ceylon_gems.dto.response.CategoryResponse;
import com.harshana.ceylon_gems.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/categories")
@RequiredArgsConstructor
public class AdminCategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryResponse> list() {
        return categoryService.list();
    }

    @PostMapping
    public CategoryResponse create(@Valid @RequestBody CategoryCreateRequest req) {
        return categoryService.create(req);
    }

    @PutMapping("/{id}")
    public CategoryResponse update(@PathVariable Long id,
                                   @Valid @RequestBody CategoryUpdateRequest req) {
        return categoryService.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}