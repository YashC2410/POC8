package com.poc8.students;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
@Autowired
private StudentRepository studentRepository;
@Autowired
private ProjectRepository projectRepository;
private String a="Success";
private String b="Failed";
private String message="No Project with Student ID: ";
private String message1="Project with ID: ";
private String message3="Student with ID: ";
private String message2=" was Found ";
public ResponseModule getProjectTest(Integer studentId) {
	List<Project> projects=projectRepository.findByStudentId(studentId);
	if(projects.isEmpty()) {
		return new ResponseModule(message+studentId+message2,Boolean.FALSE,b);
	}
	else {
		return new ResponseModule("Number of Projects found with Student ID: "+studentId+" are: "+projects.size(),Boolean.TRUE,a);
	}
}
public ResponseModule createProjectTest(Integer studentId,Project project) {
	Optional<Student> student=studentRepository.findById(studentId);
	if(student.isPresent()) {
		Student student1=studentRepository.findStudentById(studentId);
		project.setStudent(student1);
		projectRepository.save(project);
		return new ResponseModule(message1+project.getProjectId()+" created",Boolean.TRUE,a);
	}
	else {
		return new ResponseModule(message+studentId+message2,Boolean.FALSE,b);
	}
}
public ResponseModule updateProjectTest(Integer projectId,Integer studentId,Project project) {
	if(!studentRepository.existsById(studentId)) {
		return new ResponseModule(message+studentId+" was Found ",Boolean.FALSE,b);
	}
	else {
		Project newProject = projectRepository.findByProjectIdAndStudentId(projectId, studentId).get();
		newProject.setProjectName(project.getProjectName());
		newProject.setProjectTech(project.getProjectTech());
		projectRepository.save(newProject);
		return new ResponseModule(message1+projectId+" updated",Boolean.TRUE,a);
	}
}
public ResponseModule deleteProjectTest(Integer projectId,Integer studentId) {
	Optional<Project> project=projectRepository.findById(projectId);
	if(project.isPresent()) {
		projectRepository.deleteById(projectId);
		return new ResponseModule(message1+projectId+" deleted ",Boolean.TRUE,a);
	}
	else {
		return new ResponseModule(message1+project+" was not found ",Boolean.FALSE,b);
	}
}
public Project createProject(Integer studentId,Project project) throws StudentNotFoundException {
	Optional<Student> student=studentRepository.findById(studentId);
	if(student.isPresent()) {
		Student student1=studentRepository.findStudentById(studentId);
		project.setStudent(student1);
		return projectRepository.save(project);
	}
	else {
		throw new StudentNotFoundException(message3+studentId+" was not found");
	}
}
public Project getProjectById(Integer projectId) throws StudentNotFoundException{
	Optional<Project> project=projectRepository.findById(projectId);
	if(project.isPresent()) {
		return project.get();
	}
	else {
		throw new StudentNotFoundException(message1+projectId+" was not Found");
	}
}
public List<Project> getAllProjectsByStudentId(Integer studentId){
	List<Project> project=projectRepository.findByStudentId(studentId);
	if(!project.isEmpty()) {
		return project;
	}
	else {
		return new ArrayList<Project>();
	}
}
public Project updateProject(Integer projectId,Integer studentId,Project project) throws StudentNotFoundException {
	if(!studentRepository.existsById(studentId)) {
		throw new StudentNotFoundException(message3+studentId+" was not found");
	}
	return projectRepository.findById(projectId).map(newProject ->{
		newProject.setProjectName(project.getProjectName());
		newProject.setProjectTech(project.getProjectTech());
		return projectRepository.save(newProject);
	}).orElseThrow(()->new StudentNotFoundException(message1+projectId+" was not Found"));
}
public void deleteProject(Integer projectId,Integer studentId) throws StudentNotFoundException {
	Optional<Project> project=projectRepository.findByProjectIdAndStudentId(projectId, studentId);
	if(project.isPresent()) {
		projectRepository.deleteById(projectId);
	}
	else {
		throw new StudentNotFoundException(message3+studentId+" and Project with ID: "+projectId+"was not found");
	}
}
}