package com.springproject.Spring.domain.repository;

import com.springproject.Spring.domain.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeachersRepository extends JpaRepository<Teacher, Long> {
}
