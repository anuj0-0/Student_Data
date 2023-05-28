package com.kc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kc.dao.StudentDao;
import com.kc.dto.Student;

@Service
public class StudentService {

	@Autowired
	private StudentDao studentDao;

	public void saveStudent(Student student) {
		studentDao.saveStudent(student);
	}

	public void deleteStudent(int id) {

		Optional<Student> student = studentDao.getStudentById(id);
		if (student.isPresent()) {
			studentDao.deleteStudent(student.get());
		}
	}

	public List<Student> getAllStudent() {
		return studentDao.getAllStudents();
	}

	public List<Student> getStudentByPageNo(int pageNo) {
		int rowNo = 10 * (pageNo - 1);
		return studentDao.getStudentByPageNo(rowNo);
	}

	public Student getStudentById(int id) {
		Optional<Student> opt = studentDao.getStudentById(id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}
	}

	public int getTotalStudentCount() {
		return studentDao.getTotalStudentCount();
	}
}
