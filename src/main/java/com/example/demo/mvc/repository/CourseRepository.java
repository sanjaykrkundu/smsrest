package com.example.demo.mvc.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.mvc.entity.Course;

public interface CourseRepository extends PagingAndSortingRepository<Course, String>{
}
