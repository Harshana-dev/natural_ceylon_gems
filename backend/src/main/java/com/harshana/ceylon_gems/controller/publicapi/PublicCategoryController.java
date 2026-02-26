package com.harshana.ceylon_gems.controller.publicapi;

import com.harshana.ceylon_gems.dto.response.CategoryResponse;
import com.harshana.ceylon_gems.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/categories")
@RequiredArgsConstructor
public class PublicCategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryResponse> list() {
        return categoryService.list();
    }
}