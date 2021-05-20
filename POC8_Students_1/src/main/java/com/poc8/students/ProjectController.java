package com.poc8.students;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class ProjectController {
@Autowired
private ProjectService projectService;
@GetMapping("/getAllProjects/{studentId}")
public List<Project> getAllProjects(@Valid @PathVariable (value="studentId") Integer studentId){
	return projectService.getAllProjectsByStudentId(studentId);
}
@PostMapping("/{studentId}/createProject")
public Project createProject(@Valid @PathVariable (value="studentId") Integer studentId,@RequestBody Project project) throws StudentNotFoundException {
	return projectService.createProject(studentId, project);
}
@PutMapping("/{studentId}/updateProject/{projectId}")
public Project updateProject(@Valid @PathVariable (value="studentId") Integer studentId,@Valid @PathVariable (value="projectId") Integer projectId,@RequestBody Project project) throws StudentNotFoundException {
	return projectService.updateProject(projectId, studentId, project);
}
@DeleteMapping("/{studentId}/deleteProject/{projectId}")
public void deleteProject(@Valid @PathVariable (value="studentId") Integer studentId,@Valid @PathVariable (value="projectId") Integer projectId) throws StudentNotFoundException {
	projectService.deleteProject(projectId, studentId);
}
@PutMapping("{studentId}/updateProjectTest/{projectId}")
public ResponseModule updateProjectTest(@Valid @PathVariable (value="studentId") Integer studentId,@Valid @PathVariable (value="projectId") Integer projectId,@RequestBody Project project) {
	return projectService.updateProjectTest(projectId, studentId, project);
}
@PostMapping("/{studentId}/createProjectTest")
public ResponseModule createProjectTest(@Valid @PathVariable (value="studentId") Integer studentId,@RequestBody Project project) {
	return projectService.createProjectTest(studentId, project);
}
@GetMapping("/getProjectTest/{studentId}")
public ResponseModule getProjectTest(@Valid @PathVariable(value="studentId") Integer studentId) {
	return projectService.getProjectTest(studentId);
}
@DeleteMapping("/{studentId}/deleteProjectTest/{projectId}")
public ResponseModule deleteProjectTest(@Valid @PathVariable (value="studentId") Integer studentId,@Valid @PathVariable (value="projectId") Integer projectId) {
	return projectService.deleteProjectTest(projectId, studentId);
}
}
