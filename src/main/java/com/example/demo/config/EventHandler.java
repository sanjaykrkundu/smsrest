package com.example.demo.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import com.example.demo.exception.BadRequestException;
import com.example.demo.mvc.entity.Admission;
import com.example.demo.mvc.entity.Coupon;
import com.example.demo.mvc.entity.Course;
import com.example.demo.mvc.entity.Student;
import com.example.demo.mvc.repository.CouponRepository;
import com.example.demo.mvc.repository.CourseRepository;
import com.example.demo.mvc.repository.StudentRepository;

@RepositoryEventHandler
public class EventHandler {

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	CouponRepository couponRepository;

	@HandleBeforeSave
	@HandleBeforeCreate
	public void handleCourse(Course course) {
		System.out.println("Before Save/create Course");
		System.out.println("============================");
		System.out.println(course);
		System.out.println("============================");
	}

	@HandleBeforeSave
	@HandleBeforeCreate
	public void handleStudent(Student student) throws Exception {
		System.out.println("Before Save/create Student");
		System.out.println("============================");
		System.out.println(student);
		System.out.println("============================");
	}

	@HandleBeforeSave
	@HandleBeforeCreate
	public void handleCoupon(Coupon coupon) throws Exception {

		System.out.println(coupon);
		if (!coupon.getCourseId().equalsIgnoreCase("ALL.") && courseRepository.findOne(coupon.getCourseId()) == null) {
			throw new BadRequestException("Course Not Exist");
		}
	}

	@HandleBeforeSave
	@HandleBeforeCreate
	public void handleAdmission(Admission admission) throws Exception {

		Course course = courseRepository.findOne(admission.getCourseId());

		if (course == null) {
			throw new BadRequestException("Course Not Exist");
		} else if (!course.isActive()) {
			throw new BadRequestException("Course Not Active");
		}

		if (studentRepository.findOne(admission.getStudentId()) == null) {
			throw new BadRequestException("Student Not Exist");
		}

		if (admission.isInstallment()) {
			admission.setFee(course.getInstallmentFee());
		} else {
			admission.setFee(course.getFee());
		}

		if (admission.getCoupon() != null) {
			Coupon coupon = couponRepository.findOne(admission.getCoupon());

			if (coupon != null && coupon.getStartdate().before(new Date()) && coupon.getEnddate().after(new Date())
					&& (coupon.getCourseId().equalsIgnoreCase(admission.getCourseId())
							|| coupon.getCourseId().equalsIgnoreCase("ALL."))) {
				admission.setFee(admission.getFee() - (admission.getFee() * coupon.getDiscount() * 0.01f));
			} else {
				throw new BadRequestException("Invalid Coupon");
			}
		}

		System.out.println("Before Save/create Admission");
		System.out.println("============================");
		System.out.println(admission);
		System.out.println("============================");

	}

}
