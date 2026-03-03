package com.springproject.Spring.web.dto.view;

import com.springproject.Spring.web.dto.grid.CourseGridDto;
import com.springproject.Spring.web.dto.grid.UserGridDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentViewDto {

    private Long id;
    private String studentName;
    private Integer age;
    private String groupName;
    private Double gpa;
    private UserGridDto user;
    private List<CourseGridDto> courses;
}
