package com.example.demo.mvc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import com.example.demo.constant.Constants;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "admissions")
public class Admission {

	@Id
	@GenericGenerator(name = "id", strategy = "com.example.demo.hibernate.StringSequenceIdentifier", parameters = {
			@org.hibernate.annotations.Parameter(name = "prefix", value = "A"),
			@org.hibernate.annotations.Parameter(name = "length", value = "9"), 
			@org.hibernate.annotations.Parameter(name = "type", value = "MAX")})
	@GeneratedValue(generator = "id")
	@Size(min = 10, max = 10)
	@JsonProperty("id")
	private String admissionId;

	@NotNull
	@NotEmpty
	@JsonProperty("sid")
	@Size(min = Constants.STUDENT_ID_LENGTH, max = Constants.STUDENT_ID_LENGTH)
	private String studentId;

	@NotNull
	@NotEmpty
	@JsonProperty("cid")
	@Size(min = Constants.COURSE_ID_LENGTH, max = Constants.COURSE_ID_LENGTH)
	private String courseId;

	@Column(name = "registeredOn", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Date registeredDate;

	private boolean isSettled;
	private boolean isInstallment;

	@JsonProperty(access = Access.READ_ONLY)
	private float fee;

	@JsonProperty(access = Access.WRITE_ONLY)
	private transient String coupon;

	public Admission() {
		this.admissionId = null;
	}

	public String getAdmissionId() {
		return admissionId;
	}

	public void setAdmissionId(String admissionId) {
		this.admissionId = admissionId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public Date getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}

	public boolean isSettled() {
		return isSettled;
	}

	public void setSettled(boolean isSettled) {
		this.isSettled = isSettled;
	}

	public boolean isInstallment() {
		return isInstallment;
	}

	public void setInstallment(boolean isInstallment) {
		this.isInstallment = isInstallment;
	}

	public float getFee() {
		return fee;
	}

	public void setFee(float fee) {
		this.fee = fee;
	}

	public String getCoupon() {
		return coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

	@Override
	public String toString() {
		return "{\n\t\"admissionId\":" + admissionId + ",\n\t \"studentId\":" + studentId + ",\n\t \"courseId\":"
				+ courseId + ",\n\t \"registeredDate\":" + registeredDate + ",\n\t \"isSettled\":" + isSettled
				+ ",\n\t \"isInstallment\":" + isInstallment + ",\n\t \"fee\":" + fee + ",\n\t \"coupon\":" + coupon
				+ "\n}";
	}

}
