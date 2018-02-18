package com.example.demo;

import java.util.Date;

import com.example.demo.mvc.entity.Course;
import com.example.demo.mvc.entity.Student;

public class App {

	public static void main(String[] args) {
		Course c = new Course("java", (short) 12, 12343, 123434, true);
		Student s = new Student("sanjay", new Date());

	}

}
