package com.example.demo.mvc.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.example.demo.constant.Constants;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "courses")
public class Course {

	@Id
	@GenericGenerator(name = "id", strategy = "com.example.demo.hibernate.StringSequenceIdentifier", parameters = {
			@org.hibernate.annotations.Parameter(name = "prefix", value = Constants.COURSE_ID_PREFIX),
			@org.hibernate.annotations.Parameter(name = "length", value = Constants.COURSE_ID_LENGTH_WITHOUT_PREFIX) })
	@GeneratedValue(generator = "id")
	@Size(min = Constants.COURSE_ID_LENGTH, max = Constants.COURSE_ID_LENGTH)
	@JsonProperty("id")
	private String courseId;

	@Size(min = 1, max = 10)
	@NotNull
	@JsonProperty("name")
	private String courseName;

	@NotNull
	@Min(1)
	@Max(18)
	private short duration;

	private float fee;
	private float installmentFee;
	private boolean isActive;

	@ManyToMany(mappedBy = "courses")
	Set<Student> students;

	public Course() {
		courseId = null;
		students = new HashSet<Student>();
	}

	public Course(String courseName, short duration, float fee, float installmentFee, boolean isActive) {
		this();
		this.courseName = courseName;
		this.duration = duration;
		this.fee = fee;
		this.installmentFee = installmentFee;
		this.isActive = isActive;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public short getDuration() {
		return duration;
	}

	public void setDuration(short duration) {
		this.duration = duration;
	}

	public float getFee() {
		return fee;
	}

	public void setFee(float fee) {
		this.fee = fee;
	}

	public float getInstallmentFee() {
		return installmentFee;
	}

	public void setInstallmentFee(float installmentFee) {
		this.installmentFee = installmentFee;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "{\n\t\"courseId\":" + courseId + ",\n\t \"courseName\":" + courseName + ",\n\t \"duration\":" + duration
				+ ",\n\t \"fee\":" + fee + ",\n\t \"installmentFee\":" + installmentFee + ",\n\t \"isActive\":"
				+ isActive + ",\n\t \"students\":" + students + "\n}";
	}

}
