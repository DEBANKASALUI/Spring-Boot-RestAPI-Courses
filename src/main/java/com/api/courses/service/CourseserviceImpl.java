package com.api.courses.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.courses.dao.CourseDao;
import com.api.courses.entity.Course;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CourseserviceImpl implements CourseService {

	@Autowired
	private CourseDao courseDao;

	public CourseserviceImpl() {
	}

	@Override
	public List<Course> getCourses() {
		System.out.println("Fetching all courses");
		return courseDao.findAll();
	}

	@Override
	public Course getCourseById(long courseId) {
		System.out.println("Fetching course with id: " + courseId);
		try {
			Optional<Course> optionalCourse = courseDao.findById(courseId);
			if (optionalCourse.isPresent()) {
				return optionalCourse.get();
			} else {
				throw new EntityNotFoundException("Course not found with id: " + courseId);
			}
		} catch (EntityNotFoundException e) {
			System.out.println("Course not found with id: " + courseId);
			throw e;
		}
	}

	@Override
	public Course addCourse(Course c) {
		System.out.println("Adding course: " + c);
		return courseDao.save(c);
	}

	@Override
	public Course updateCourse(Course course) {
		System.out.println("Updating course with id: " + course.getId());
		try {
			if (courseDao.existsById(course.getId())) {
				return courseDao.save(course);
			} else {
				throw new EntityNotFoundException("Course not found with id: " + course.getId());
			}
		} catch (EntityNotFoundException e) {
			System.out.println("Course not found with id: " + course.getId());
			throw e;
		}
	}

	@Override
	public boolean deleteCourse(long id) {
		System.out.println("Deleting course with id: " + id);
		try {
			if (courseDao.existsById(id)) {
				courseDao.deleteById(id);
				return true;
			} else {
				throw new EntityNotFoundException("Course not found with id: " + id);
			}
		} catch (EntityNotFoundException e) {
			System.out.println("Course not found with id: " + id);
			throw e;
		}
	}

//	Without using DB
//	List<Course> coursesList = new ArrayList<>();
//
//	public CourseserviceImpl() {
//		coursesList.add(new Course(1001, "Java", "Java Crash Course", 500.99, 1));
//		coursesList.add(new Course(1002, "Python", "Python Crash Course", 699.99, 3));
//		coursesList.add(new Course(1003, "Golang", "Golang Crash Course", 800.99, 2));
//		coursesList.add(new Course(1004, "Javascript", "Javascript Crash Course", 1000.99, 5));
//	}
//
//	@Override
//	public List<Course> getCourses() {
//		coursesList.stream().forEach(System.out::println);
//		return coursesList;
//	}
//
//	@Override
//	public Course getCourseById(long courseId) {
//		Optional<Course> course = coursesList.stream().filter(e -> e.getId() == courseId).findFirst();
//			if (course.isPresent()) {
//				System.out.println("Course Present: " + course);
//				return course.get();
//			} else {
//				System.out.println("Course with id: " + courseId + " not present");
//			}
//		return null;
//	}
//
//	@Override
//	public Course addCourse(Course c) {
//		coursesList.add(c);
//		System.out.println("Course Added Successfully with id:" + c.getId());
//		return c;
//	}
//
//	@Override
//	public Course updateCourse(Course course) {
//		Optional<Course> c = coursesList.stream().filter(i -> i.getId() == course.getId()).findFirst();
//		if (c.isPresent()) {
//			c.get().setName(course.getName());
//			c.get().setDescription(course.getDescription());
//			c.get().setPrice(course.getPrice());
//			c.get().setDuration(course.getDuration());
//			System.out.println("Course with ID:" + course.getId() + " has been updated successfully");
//			return course;
//		} else {
//			System.out.println("Invalid Course Id: " + course.getId());
//		}
//		return null;
//	}
//
//	@Override
//	public boolean deleteCourse(long id) {
//		boolean removed = coursesList.removeIf(i -> i.getId() == id);
//		if (removed) {
//			System.out.println("Course with id:" + id + " has been deleted successfully!!");
//		} else {
//			System.out.println("Invalid book id: " + id);
//		}
//		return removed;
//	}

}
