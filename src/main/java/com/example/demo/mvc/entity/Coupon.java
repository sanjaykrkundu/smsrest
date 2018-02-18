package com.example.demo.mvc.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.constant.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "coupon")
public class Coupon {

	@Id
	@NotEmpty
	@NotNull
	@Size(min = 3, max = 15)
	@JsonProperty("code")
	private String couponCode;

	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date startdate;

	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date enddate;

	@NotNull
	@NotEmpty
	@JsonProperty("cid")
	@Size(min = Constants.COURSE_ID_LENGTH, max = Constants.COURSE_ID_LENGTH)
	private String courseId;

	@NotNull
	@Min(1)
	@Max(100)
	private short discount;

	public Coupon() {

	}

	public Coupon(String couponCode, Date startdate, Date enddate, String courseId, short discount) {
		super();
		this.couponCode = couponCode;
		this.startdate = startdate;
		this.enddate = enddate;
		this.courseId = courseId;
		this.discount = discount;
	}


	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public short getDiscount() {
		return discount;
	}

	public void setDiscount(short discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		return "{\n\t\"couponCode\":" + couponCode + ",\n\t \"startdate\":" + startdate + ",\n\t \"enddate\":" + enddate
				+ ",\n\t \"courseId\":" + courseId + ",\n\t \"discount\":" + discount + "\n}";
	}

}
