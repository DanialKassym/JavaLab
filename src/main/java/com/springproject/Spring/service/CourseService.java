package com.springproject.Spring.service;

import com.springproject.Spring.domain.entity.Course;
import com.springproject.Spring.domain.entity.Student;
import com.springproject.Spring.domain.entity.Teacher;
import com.springproject.Spring.domain.repository.CoursesRepository;
import com.springproject.Spring.domain.repository.StudentsRepository;
import com.springproject.Spring.domain.repository.TeachersRepository;
import com.springproject.Spring.exception.EntityNotFoundException;
import com.springproject.Spring.web.converter.CourseConverter;
import com.springproject.Spring.web.dto.form.CourseFormDto;
import com.springproject.Spring.web.dto.grid.StudentGridDto;
import com.springproject.Spring.web.dto.grid.TeacherGridDto;
import com.springproject.Spring.web.dto.view.CourseViewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseService {
    private final CoursesRepository coursesRepository;
    private final TeachersRepository teachersRepository;
    private final StudentsRepository studentsRepository;
    private final CourseConverter courseConverter;

    public List<CourseViewDto> findAllView() {
        return coursesRepository.findAll().stream().map(courseConverter::toViewDto).toList();
    }

    public List<TeacherGridDto> findAllTeachers() {
        return teachersRepository.findAll().stream().map(courseConverter::toTeacherGridDto).toList();
    }

    public List<StudentGridDto> findAllStudents() {
        return studentsRepository.findAll().stream().map(courseConverter::toStudentGridDto).toList();
    }

    public CourseFormDto getForm(Long id) {
        return id == null ? new CourseFormDto() : courseConverter.toFormDto(findById(id));
    }

    public void create(CourseFormDto form) {
        Course course = new Course();
        applyForm(form, course);
        coursesRepository.save(course);
    }

    public void update(Long id, CourseFormDto form) {
        Course course = findById(id);
        applyForm(form, course);
        coursesRepository.save(course);
    }

    public void delete(Long id) {
        Course course = findById(id);
        if (course.getStudents() != null) {
            for (Student student : course.getStudents()) {
                if (student.getCourses() != null) {
                    student.getCourses().remove(course);
                }
            }
            course.getStudents().clear();
        }
        coursesRepository.deleteById(id);
    }

    public Course findById(Long id) {
        return coursesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + id));
    }

    private void applyForm(CourseFormDto form, Course course) {
        Teacher teacher = teachersRepository.findById(form.getTeacherId())
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found with id: " + form.getTeacherId()));

        if (course.getStudents() != null) {
            for (Student oldStudent : course.getStudents()) {
                if (oldStudent.getCourses() != null) {
                    oldStudent.getCourses().remove(course);
                }
            }
            course.getStudents().clear();
        }

        Set<Student> students = new HashSet<>();
        if (form.getStudentIds() != null) {
            students = new HashSet<>(studentsRepository.findAllById(form.getStudentIds()));
        }

        courseConverter.applyFormToEntity(form, course, teacher, students);

        for (Student student : students) {
            Set<Course> studentCourses = student.getCourses() == null ? new HashSet<>() : student.getCourses();
            studentCourses.add(course);
            student.setCourses(studentCourses);
        }
    }
}
