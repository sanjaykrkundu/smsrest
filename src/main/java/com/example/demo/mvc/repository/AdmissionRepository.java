package com.example.demo.mvc.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.mvc.entity.Admission;

public interface AdmissionRepository extends PagingAndSortingRepository<Admission, String> {

}
