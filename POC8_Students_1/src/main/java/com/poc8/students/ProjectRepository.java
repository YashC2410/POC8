package com.poc8.students;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository <Project , Integer>{
	List<Project> findByStudentId(Integer id);
	Optional<Project> findByProjectIdAndStudentId(Integer projectId,Integer studentId);

}
