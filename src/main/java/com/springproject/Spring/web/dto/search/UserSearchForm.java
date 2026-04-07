package com.springproject.Spring.web.dto.search;

import com.springproject.Spring.domain.enums.UserRole;
import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class UserSearchForm {
    private String username;
    private String email;
    private UserRole role;
    private String sortBy;
    private Sort.Direction sortDirection;
}
