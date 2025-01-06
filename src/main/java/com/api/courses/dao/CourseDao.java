package com.api.courses.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.courses.entity.Course;

@Repository
public interface CourseDao extends JpaRepository<Course, Long>{

}
