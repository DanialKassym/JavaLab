package com.springproject.Spring.web.dto.form;

import com.springproject.Spring.domain.enums.Department;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherFormDto {

    private Long id;

    @NotBlank(message = "Teacher name is required")
    @Size(min = 2, max = 100, message = "Teacher name must be between 2 and 100 characters")
    private String teacherName;

    @NotNull(message = "Experience years is required")
    @Min(value = 0, message = "Experience years cannot be negative")
    @Max(value = 60, message = "Experience years must be at most 60")
    private Integer experienceYears;

    @NotNull(message = "Department is required")
    private Department department;

    @NotNull(message = "User is required")
    private Long userId;

    private List<Long> courseIds;
}
