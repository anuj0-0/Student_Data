package com.kc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kc.dto.PageNo;
import com.kc.dto.Student;
import com.kc.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping("/save")
	@ResponseBody
	public String saveStudent(@RequestBody Student student) {
		studentService.saveStudent(student);
		return "OK";
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable int id) {
		studentService.deleteStudent(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/id")
	public Student getStudentById(@RequestParam int id) {
		Student student = studentService.getStudentById(id);
		return student;
	}

	@GetMapping("/all")
	@ResponseBody
	public List<Student> getAllStudent() {
		return studentService.getAllStudent();
	}

	@GetMapping()
	public ModelAndView show(ModelAndView model) {

		model.setViewName("home.html");
		return model;
	}

	@PostMapping("/page")
	@ResponseBody
	public List<Student> getStudentByPageNo(@RequestBody PageNo pageNo) {
		return studentService.getStudentByPageNo(pageNo.getPageNo());
	}

	@GetMapping("/count")
	@ResponseBody
	public int getTotalStudentCount() {
		return studentService.getTotalStudentCount();
	}

}
