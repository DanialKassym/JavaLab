package com.springproject.Spring.domain.repository;

import com.springproject.Spring.domain.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CoursesRepository extends JpaRepository<Course, Long>, JpaSpecificationExecutor<Course> {
}
