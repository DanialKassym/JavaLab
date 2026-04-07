package com.springproject.Spring.web.dto.search;

import com.springproject.Spring.domain.enums.Department;
import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class TeacherSearchForm {
    private String name;
    private Department department;
    private Integer experienceYearsFrom;
    private Integer experienceYearsTo;
    private String sortBy;
    private Sort.Direction sortDirection;
}
