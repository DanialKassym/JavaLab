package com.springproject.Spring.Students;

import com.springproject.Spring.Courses.Course;
import com.springproject.Spring.Courses.CoursesRepository;
import com.springproject.Spring.Users.UsersRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/students")
public class StudentsController {

    private final StudentsRepository studentRepository;
    private final UsersRepository userRepository;
    private final CoursesRepository courseRepository;

    public StudentsController(StudentsRepository studentRepository,
                              UsersRepository userRepository,
                              CoursesRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping
    public String read(
            @RequestParam(name = "id", required = false) Long id,
            Model model) {

        Student form = (id == null)
                ? new Student()
                : studentRepository.getById(id);

        model.addAttribute("form", form);
        model.addAttribute("editMode", id != null);
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("courses", courseRepository.findAll());

        return "students";
    }

    @PostMapping
    public String create(
            Student student,
            @RequestParam Long userId,
            @RequestParam(required = false) List<Long> courseIds) {

        student.setUser(userRepository.getById(userId));

        if (courseIds != null) {
            Set<Course> courses = new HashSet<>(courseRepository.findAllById(courseIds));
            student.setCourses(courses);
        }

        studentRepository.save(student);
        return "redirect:/students";
    }

    @PutMapping("/{id}")
    public String update(
            @PathVariable Long id,
            Student form,
            @RequestParam Long userId,
            @RequestParam(required = false) List<Long> courseIds) {

        Student model = studentRepository.getById(id);

        model.setStudentName(form.getStudentName());
        model.setUser(userRepository.getById(userId));

        Set<Course> courses = (courseIds == null)
                ? new HashSet<>()
                : new HashSet<>(courseRepository.findAllById(courseIds));

        model.setCourses(courses);

        studentRepository.save(model);
        return "redirect:/students";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        Student student = studentRepository.getById(id);

        for (Course course : student.getCourses()) {
            course.getStudents().remove(student);
        }
        student.getCourses().clear();

        studentRepository.delete(student);
        return "redirect:/students";
    }
}
