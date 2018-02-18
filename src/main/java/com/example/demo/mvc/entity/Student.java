package com.example.demo.mvc.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "students")
public class Student {
	@Id
	@GenericGenerator(name = "sid", strategy = "com.example.demo.hibernate.StringSequenceIdentifier", parameters = {
			@org.hibernate.annotations.Parameter(name = "prefix", value = "S"),
			@org.hibernate.annotations.Parameter(name = "length", value = "6") })
	@GeneratedValue(generator = "sid")
	@Size(min = 7, max = 7)
	@JsonProperty("id")

	private String studentId;

	@NotNull
	@Size(min = 3, max = 20)
	@JsonProperty("name")
	private String studentName;

	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dob;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "admissions", joinColumns = { @JoinColumn(name = "studentId") }, inverseJoinColumns = {
			@JoinColumn(name = "courseId") })
	Set<Course> courses;

	public Student() {
		studentId = null;
		courses = new HashSet<>();
	}

	public Student(String studentName, Date dob) {
		this();
		this.studentName = studentName;
		this.dob = dob;
	}

	public Student(String studentId, String studentName, Date dob) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.dob = dob;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentId() {
		return studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "{\n\t\"studentId\":" + studentId + ",\n\t \"studentName\":" + studentName + ",\n\t \"dob\":" + dob
				+ ",\n\t \"courses\":" + courses + "\n}";
	}

}
