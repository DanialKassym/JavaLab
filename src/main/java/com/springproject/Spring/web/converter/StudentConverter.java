package com.springproject.Spring.web.converter;

import com.springproject.Spring.domain.entity.Course;
import com.springproject.Spring.domain.entity.Student;
import com.springproject.Spring.domain.entity.User;
import com.springproject.Spring.web.dto.form.StudentFormDto;
import com.springproject.Spring.web.dto.grid.CourseGridDto;
import com.springproject.Spring.web.dto.grid.UserGridDto;
import com.springproject.Spring.web.dto.view.StudentViewDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentConverter {

    public void applyFormToEntity(StudentFormDto form, Student student, User user, java.util.Set<Course> courses) {
        student.setStudentName(form.getStudentName());
        student.setAge(form.getAge());
        student.setGpa(form.getGpa());
        student.setGroupName(form.getGroupName());
        student.setUser(user);
        student.setCourses(courses);
    }

    public StudentFormDto toFormDto(Student student) {
        StudentFormDto dto = new StudentFormDto();
        dto.setId(student.getId());
        dto.setStudentName(student.getStudentName());
        dto.setAge(student.getAge());
        dto.setGpa(student.getGpa());
        dto.setGroupName(student.getGroupName());
        if (student.getUser() != null) {
            dto.setUserId(student.getUser().getId());
        }
        if (student.getCourses() != null) {
            dto.setCourseIds(student.getCourses().stream().map(Course::getId).toList());
        }
        return dto;
    }

    public StudentViewDto toViewDto(Student student) {
        StudentViewDto dto = new StudentViewDto();
        dto.setId(student.getId());
        dto.setStudentName(student.getStudentName());
        dto.setAge(student.getAge());
        dto.setGpa(student.getGpa());
        dto.setGroupName(student.getGroupName());
        if (student.getUser() != null) {
            dto.setUser(toUserGridDto(student.getUser()));
        }
        List<CourseGridDto> courseDtos = new ArrayList<>();
        if (student.getCourses() != null) {
            for (Course course : student.getCourses()) {
                courseDtos.add(toCourseGridDto(course));
            }
        }
        dto.setCourses(courseDtos);
        return dto;
    }

    public UserGridDto toUserGridDto(User user) {
        if (user == null) {
            return null;
        }
        UserGridDto dto = new UserGridDto();
        dto.setId(user.getId());
        dto.setUserName(user.getUserName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }

    public CourseGridDto toCourseGridDto(Course course) {
        if (course == null) {
            return null;
        }
        CourseGridDto dto = new CourseGridDto();
        dto.setId(course.getId());
        dto.setCourseName(course.getCourseName());
        dto.setCredits(course.getCredits());
        dto.setMaxStudents(course.getMaxStudents());
        return dto;
    }
}
