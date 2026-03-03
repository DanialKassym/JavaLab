package com.springproject.Spring.web.dto.form;

import com.springproject.Spring.domain.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFormDto {

    private Long id;
    private String userName;
    private String email;
    private String password;
    private UserRole role;
}
