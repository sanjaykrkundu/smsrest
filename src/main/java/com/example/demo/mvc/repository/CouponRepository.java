package com.example.demo.mvc.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.mvc.entity.Coupon;

public interface CouponRepository extends PagingAndSortingRepository<Coupon, String> {
	
}
