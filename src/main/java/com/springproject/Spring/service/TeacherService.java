package com.springproject.Spring.service;

import com.springproject.Spring.domain.entity.Course;
import com.springproject.Spring.domain.entity.Teacher;
import com.springproject.Spring.domain.entity.User;
import com.springproject.Spring.domain.repository.CoursesRepository;
import com.springproject.Spring.domain.repository.TeachersRepository;
import com.springproject.Spring.domain.repository.UsersRepository;
import com.springproject.Spring.exception.BusinessException;
import com.springproject.Spring.exception.EntityNotFoundException;
import com.springproject.Spring.web.converter.TeacherConverter;
import com.springproject.Spring.web.dto.form.TeacherFormDto;
import com.springproject.Spring.web.dto.grid.CourseGridDto;
import com.springproject.Spring.web.dto.grid.UserGridDto;
import com.springproject.Spring.web.dto.view.TeacherViewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TeacherService {
    private final TeachersRepository teachersRepository;
    private final UsersRepository usersRepository;
    private final CoursesRepository coursesRepository;
    private final TeacherConverter teacherConverter;

    public List<TeacherViewDto> findAllView() {
        return teachersRepository.findAll().stream().map(teacherConverter::toViewDto).toList();
    }

    public List<UserGridDto> findAllUsers() {
        return usersRepository.findAll().stream().map(teacherConverter::toUserGridDto).toList();
    }

    public List<CourseGridDto> findAllCourses() {
        return coursesRepository.findAll().stream().map(teacherConverter::toCourseGridDto).toList();
    }

    public TeacherFormDto getForm(Long id) {
        return id == null ? new TeacherFormDto() : teacherConverter.toFormDto(findById(id));
    }

    public void create(TeacherFormDto form) {
        Teacher teacher = new Teacher();
        applyForm(form, teacher);
        teachersRepository.save(teacher);
    }

    public void update(Long id, TeacherFormDto form) {
        Teacher teacher = findById(id);
        applyForm(form, teacher);
        teachersRepository.save(teacher);
    }

    public void delete(Long id) {
        Teacher teacher = findById(id);
        if (teacher.getCourses() != null && !teacher.getCourses().isEmpty()) {
            throw new BusinessException("Cannot delete teacher with assigned courses");
        }
        teachersRepository.delete(teacher);
    }

    public Teacher findById(Long id) {
        return teachersRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Teacher not found with id: " + id));
    }

    private void applyForm(TeacherFormDto form, Teacher teacher) {
        User user = usersRepository.findById(form.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + form.getUserId()));
        List<Long> courseIds = form.getCourseIds();
        List<Course> courses = courseIds == null ? new ArrayList<>() : new ArrayList<>(coursesRepository.findAllById(courseIds));
        teacherConverter.applyFormToEntity(form, teacher, user, courses);
    }
}
