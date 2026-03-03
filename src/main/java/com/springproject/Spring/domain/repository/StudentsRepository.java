package com.springproject.Spring.domain.repository;

import com.springproject.Spring.domain.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsRepository extends JpaRepository<Student, Long> {
}
