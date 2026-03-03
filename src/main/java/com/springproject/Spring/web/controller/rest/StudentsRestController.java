package com.springproject.Spring.web.controller.rest;

import com.springproject.Spring.service.StudentService;
import com.springproject.Spring.web.dto.form.StudentFormDto;
import com.springproject.Spring.web.dto.view.StudentViewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentsRestController {
    private final StudentService studentService;

    @GetMapping
    public List<StudentViewDto> read() {
        return studentService.findAllView();
    }

    @PostMapping
    public void create(@RequestBody StudentFormDto form) {
        studentService.create(form);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody StudentFormDto form) {
        studentService.update(id, form);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        studentService.delete(id);
    }
}
