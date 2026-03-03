package com.springproject.Spring.web.dto.form;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseFormDto {

    private Long id;
    private String courseName;
    private Integer credits;
    private Integer maxStudents;
    private Long teacherId;
    private List<Long> studentIds;
}
