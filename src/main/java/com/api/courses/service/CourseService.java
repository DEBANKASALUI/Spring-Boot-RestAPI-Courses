package com.api.courses.service;

import java.util.List;

import com.api.courses.entity.Course;

public interface CourseService {

	public List<Course> getCourses();

	public Course getCourseById(long courseId);

	public Course addCourse(Course c);

	public Course updateCourse(Course course);

	public boolean deleteCourse(long id);

}
