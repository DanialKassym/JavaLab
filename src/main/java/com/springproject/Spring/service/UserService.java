package com.springproject.Spring.service;

import com.springproject.Spring.domain.entity.User;
import com.springproject.Spring.domain.repository.UsersRepository;
import com.springproject.Spring.exception.EntityNotFoundException;
import com.springproject.Spring.web.converter.UserConverter;
import com.springproject.Spring.web.dto.form.UserFormDto;
import com.springproject.Spring.web.dto.grid.UserGridDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UsersRepository usersRepository;
    private final UserConverter userConverter;

    public List<UserGridDto> findAllView() {
        return usersRepository.findAll().stream().map(userConverter::toGridDto).toList();
    }

    public UserFormDto getForm(Long id) {
        return id == null ? new UserFormDto() : userConverter.toFormDto(findById(id));
    }

    public void create(UserFormDto form) {
        User user = new User();
        userConverter.applyFormToEntity(form, user);
        usersRepository.save(user);
    }

    public void update(Long id, UserFormDto form) {
        User user = findById(id);
        userConverter.applyFormToEntity(form, user);
        usersRepository.save(user);
    }

    public void delete(Long id) {
        usersRepository.delete(findById(id));
    }

    public User findById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }
}
