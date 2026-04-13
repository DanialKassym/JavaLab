package com.springproject.Spring.web.controller.api;

import com.springproject.Spring.service.UserService;
import com.springproject.Spring.web.dto.form.UserFormDto;
import com.springproject.Spring.web.dto.grid.UserGridDto;
import com.springproject.Spring.web.dto.search.UserSearchForm;
import com.springproject.Spring.web.validations.BindingResultValidationUtils;
import com.springproject.Spring.web.validations.UserFormValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersRestController {
    private final UserService userService;
    private final UserFormValidator userFormValidator;

    @GetMapping
    public Page<UserGridDto> read(UserSearchForm searchForm,
                                  @PageableDefault(size = 10) Pageable pageable) {
        return userService.search(searchForm, pageable);
    }

    @PostMapping
    public void create(@RequestBody UserFormDto form) {
        BeanPropertyBindingResult br = new BeanPropertyBindingResult(form, "form");
        userFormValidator.validate(form, br, null);
        BindingResultValidationUtils.validate(br);
        userService.create(form);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody UserFormDto form) {
        BeanPropertyBindingResult br = new BeanPropertyBindingResult(form, "form");
        userFormValidator.validate(form, br, id);
        BindingResultValidationUtils.validate(br);
        userService.update(id, form);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
