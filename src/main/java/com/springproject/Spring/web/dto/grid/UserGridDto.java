package com.springproject.Spring.web.dto.grid;

import com.springproject.Spring.domain.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGridDto {

    private Long id;
    private String userName;
    private String email;
    private UserRole role;
}
