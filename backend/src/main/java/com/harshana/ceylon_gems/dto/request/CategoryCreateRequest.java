package com.harshana.ceylon_gems.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryCreateRequest(
        @NotBlank @Size(min = 2, max = 80) String name
) {}