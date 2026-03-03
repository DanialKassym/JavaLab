package com.springproject.Spring.service;

import com.springproject.Spring.domain.entity.Course;
import com.springproject.Spring.domain.entity.Student;
import com.springproject.Spring.domain.entity.User;
import com.springproject.Spring.domain.repository.CoursesRepository;
import com.springproject.Spring.domain.repository.StudentsRepository;
import com.springproject.Spring.domain.repository.UsersRepository;
import com.springproject.Spring.exception.EntityNotFoundException;
import com.springproject.Spring.web.converter.StudentConverter;
import com.springproject.Spring.web.dto.form.StudentFormDto;
import com.springproject.Spring.web.dto.grid.CourseGridDto;
import com.springproject.Spring.web.dto.grid.UserGridDto;
import com.springproject.Spring.web.dto.view.StudentViewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentService {
    private final StudentsRepository studentRepository;
    private final UsersRepository userRepository;
    private final CoursesRepository courseRepository;
    private final StudentConverter studentConverter;

    public List<StudentViewDto> findAllView() {
        return studentRepository.findAll().stream().map(studentConverter::toViewDto).toList();
    }

    public List<UserGridDto> findAllUsers() {
        return userRepository.findAll().stream().map(studentConverter::toUserGridDto).toList();
    }

    public List<CourseGridDto> findAllCourses() {
        return courseRepository.findAll().stream().map(studentConverter::toCourseGridDto).toList();
    }

    public StudentFormDto getForm(Long id) {
        return id == null ? new StudentFormDto() : studentConverter.toFormDto(findById(id));
    }

    public void create(StudentFormDto form) {
        Student student = new Student();
        applyForm(form, student);
        studentRepository.save(student);
    }

    public void update(Long id, StudentFormDto form) {
        Student student = findById(id);
        applyForm(form, student);
        studentRepository.save(student);
    }

    public void delete(Long id) {
        Student student = findById(id);
        if (student.getCourses() != null) {
            for (Course course : student.getCourses()) {
                if (course.getStudents() != null) {
                    course.getStudents().remove(student);
                }
            }
            student.getCourses().clear();
        }
        studentRepository.delete(student);
    }

    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));
    }

    private void applyForm(StudentFormDto form, Student student) {
        User user = userRepository.findById(form.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + form.getUserId()));
        List<Long> courseIds = form.getCourseIds();
        Set<Course> courses = courseIds == null ? new HashSet<>() : new HashSet<>(courseRepository.findAllById(courseIds));
        studentConverter.applyFormToEntity(form, student, user, courses);
    }
}
