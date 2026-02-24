package com.springproject.Spring.Teachers;

import com.springproject.Spring.Courses.Course;
import com.springproject.Spring.Courses.CoursesRepository;
import com.springproject.Spring.Students.Student;
import com.springproject.Spring.Students.StudentsController;
import com.springproject.Spring.Students.StudentsRepository;
import com.springproject.Spring.Users.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/teachers")
public class TeachersController {

    private static final Logger logger = LoggerFactory.getLogger(StudentsController.class);
    private final TeachersRepository teachersRepository;
    private final UsersRepository usersRepository;
    private final CoursesRepository courseRepository;
    private final StudentsRepository studentRepository;

    public TeachersController(TeachersRepository teachersRepository,
                              UsersRepository usersRepository,
                              CoursesRepository courseRepository, StudentsRepository studentRepository) {
        this.teachersRepository = teachersRepository;
        this.usersRepository = usersRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    // READ + FORM
    @GetMapping
    public String read(
            @RequestParam(name = "id", required = false) Long id,
            Model model
    ) {
        Teacher form = (id != null)
                ? teachersRepository.getById(id)
                : new Teacher();

        model.addAttribute("editMode", id != null);
        model.addAttribute("form", form);
        model.addAttribute("teachers", teachersRepository.findAll());
        model.addAttribute("users", usersRepository.findAll());
        model.addAttribute("courses", courseRepository.findAll());

        return "teachers";
    }

    @PostMapping
    public String create(Teacher teacher,
    @RequestParam Long userId,
                         @RequestParam(required = false) List<Long> courseIds) {

        Student student = studentRepository.getById(userId);
        if (userId.equals(student.getId())){
            logger.info("The teacher you are trying to assign is already student");
            return "redirect:/teachers";
        }
        if (courseIds != null ) {
            List<Course> courses = new ArrayList<>(courseRepository.findAllById(courseIds));
            teacher.setCourses(courses);
        }
        teacher.setUser(usersRepository.getById(userId));
        teachersRepository.save(teacher);
        return "redirect:/teachers";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable(name = "id") Long id,
                         Teacher teacherForm,
                         @RequestParam Long userId,
                         @RequestParam(required = false) List<Long> courseIds) {

        Teacher teacherModel = teachersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        teacherModel.setTeacherName(teacherForm.getTeacherName());
        teacherModel.setUser(usersRepository.getById(userId));

        List<Course> courses = (courseIds == null)
                ? new ArrayList<>()
                : new ArrayList<>(courseRepository.findAllById(courseIds));

        teacherModel.setCourses(courses);
        teachersRepository.save(teacherModel);
        return "redirect:/teachers";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        Teacher teacher = teachersRepository.getById(id);

        for (Course course : teacher.getCourses()) {
            course.getStudents().remove(teacher);
        }
        teacher.getCourses().clear();

        teachersRepository.delete(teacher);
        return "redirect:/teachers";
    }
}
