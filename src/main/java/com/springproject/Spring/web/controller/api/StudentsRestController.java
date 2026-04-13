package com.springproject.Spring.web.controller.api;

import com.springproject.Spring.service.StudentService;
import com.springproject.Spring.web.dto.form.StudentFormDto;
import com.springproject.Spring.web.dto.search.StudentSearchForm;
import com.springproject.Spring.web.dto.view.StudentViewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentsRestController {
    private final StudentService studentService;

    @GetMapping
    public Page<StudentViewDto> read(StudentSearchForm form,
                                     @PageableDefault(size = 10) Pageable pageable) {
        return studentService.search(form, pageable);
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
