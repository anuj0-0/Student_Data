package com.kc.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kc.dto.Student;
import com.kc.repository.StudentRepository;

@Repository
public class StudentDao {

	@Autowired
	private StudentRepository studentRepository;

	public Student saveStudent(Student user) {
		return studentRepository.save(user);
	}

	public String deleteStudent(Student user) {
		studentRepository.delete(user);
		return "Deleted";
	}

	public Optional<Student> getStudentById(int studentId) {
		return studentRepository.findById(studentId);
	}

	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	public List<Student> getByName(String name) {
		return studentRepository.findByName(name);
	}

	public List<Student> getStudentByPageNo(int rowNo) {
		return studentRepository.getStudentByPageNo(rowNo);
	}

	public int getTotalStudentCount() {
		return studentRepository.getTotalStudentCount();
	}

}
