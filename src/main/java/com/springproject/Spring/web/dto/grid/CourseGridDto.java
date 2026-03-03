package com.springproject.Spring.web.dto.grid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseGridDto {

    private Long id;
    private String courseName;
    private Integer credits;
    private Integer maxStudents;
}
