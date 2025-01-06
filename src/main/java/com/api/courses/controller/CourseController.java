package com.api.courses.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.courses.entity.Course;
import com.api.courses.service.CourseService;

import jakarta.persistence.EntityNotFoundException;

@RestController
public class CourseController {

	@Autowired
	private CourseService courseService;

	@GetMapping("/home")
	public String home() {
		return "Welcome to Courses Application";
	}

	@GetMapping("/courses")
	public List<Course> getCourses() {
		return courseService.getCourses();
	}

	@GetMapping("/courses/{courseId}")
	public ResponseEntity<?> getCourseById(@PathVariable("courseId") long courseId) {
		try {
			Course c = courseService.getCourseById(courseId);
			return new ResponseEntity<>(c, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>("ID not found in database", HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/courses")
	public Course addCourse(@RequestBody Course c) {
		return courseService.addCourse(c);
	}

	@PutMapping("/courses")
	public ResponseEntity<?> updateCourse(@RequestBody Course course) {
		try {
			Course c = courseService.updateCourse(course);
			return new ResponseEntity<>(c, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>("ID not found in database", HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/courses/{id}")
	public ResponseEntity<?> deleteCourseByID(@PathVariable String id) {
		try {
			boolean isDeleted = courseService.deleteCourse(Long.parseLong(id));
			if (isDeleted) {
				return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("ID not found in database", HttpStatus.NOT_FOUND);
			}
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>("ID not found in database", HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
