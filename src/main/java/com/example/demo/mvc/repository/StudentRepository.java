package com.example.demo.mvc.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.mvc.entity.Student;

public interface StudentRepository extends PagingAndSortingRepository<Student, String>{

}
