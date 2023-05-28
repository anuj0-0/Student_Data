package com.kc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kc.dto.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	public List<Student> findByName(String name);

	@Query(value = "SELECT * FROM Student ORDER BY id Limit 10 OFFSET ?1", nativeQuery = true)
	public List<Student> getStudentByPageNo(int rowNo);

	@Query(value = "SELECT COUNT(s.id) FROM Student s")
	public int getTotalStudentCount();
}
