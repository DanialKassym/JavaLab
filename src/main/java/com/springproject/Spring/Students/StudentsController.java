package com.springproject.Spring.Students;

import com.springproject.Spring.Courses.Course;
import com.springproject.Spring.Courses.CoursesRepository;
import com.springproject.Spring.Teachers.Teacher;
import com.springproject.Spring.Teachers.TeachersRepository;
import com.springproject.Spring.Users.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/students")
public class StudentsController {
    private static final Logger logger = LoggerFactory.getLogger(StudentsController.class);

    private final TeachersRepository teachersRepository;
    private final StudentsRepository studentRepository;
    private final UsersRepository userRepository;
    private final CoursesRepository courseRepository;

    public StudentsController(TeachersRepository teachersRepository, StudentsRepository studentRepository,
                              UsersRepository userRepository,
                              CoursesRepository courseRepository) {
        this.teachersRepository = teachersRepository;
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

        Teacher teacher = teachersRepository.getById(userId);
        if (userId.equals(teacher.getId())){
            logger.info("The student you are trying to assign is already teacher");
            return "redirect:/students";
        }


        if (courseIds != null) {
            Set<Course> courses = new HashSet<>(courseRepository.findAllById(courseIds));
            student.setCourses(courses);
        }
        student.setUser(userRepository.getById(userId));
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
