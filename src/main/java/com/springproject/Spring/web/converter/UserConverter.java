package com.springproject.Spring.web.converter;

import com.springproject.Spring.domain.entity.User;
import com.springproject.Spring.web.dto.form.UserFormDto;
import com.springproject.Spring.web.dto.grid.UserGridDto;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public void applyFormToEntity(UserFormDto form, User user) {
        user.setUserName(form.getUserName());
        user.setEmail(form.getEmail());
        if (form.getPassword() != null && !form.getPassword().isBlank()) {
            user.setPassword(form.getPassword());
        }
        user.setRole(form.getRole());
    }

    public UserFormDto toFormDto(User user) {
        UserFormDto dto = new UserFormDto();
        dto.setId(user.getId());
        dto.setUserName(user.getUserName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }

    public UserGridDto toGridDto(User user) {
        UserGridDto dto = new UserGridDto();
        dto.setId(user.getId());
        dto.setUserName(user.getUserName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }
}
