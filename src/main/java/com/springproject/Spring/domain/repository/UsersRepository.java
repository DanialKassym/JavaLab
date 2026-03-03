package com.springproject.Spring.domain.repository;


import com.springproject.Spring.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {
}
