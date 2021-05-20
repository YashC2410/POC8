package com.poc8.students;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student , Integer>{
	public Student findStudentById(Integer id);
	public Student findStudentByEmail(String email);
	Page<Student> findAll(Pageable pageable);
}
