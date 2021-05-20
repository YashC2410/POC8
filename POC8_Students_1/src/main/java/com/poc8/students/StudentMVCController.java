package com.poc8.students;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
@Controller
@RequestMapping("/")
public class StudentMVCController {
@Autowired
private StudentRepository studentRepository;
@Autowired
private StudentService studentService;
@Autowired
private ProjectService projectService;
private String a="student";
private String b="projects";
private String c="project";
private String d="redirect:/project/";
private String e="redirect:/";
@GetMapping("/")
public String viewHomePage(Model model) {
	return findPaginated(1, "fname", "asc", model);		
}
//PageNumber selection
@GetMapping("/page/{pageNo}")
public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
		@RequestParam("sortField") String sortField,
		@RequestParam("sortDir") String sortDir,
		Model model) {
	int pageSize = 5;
	
	Page<Student> page = studentService.findPaginated(pageNo, pageSize, sortField, sortDir);
	List<Student> students = page.getContent();
	
	model.addAttribute("currentPage", pageNo);
	model.addAttribute("totalPages", page.getTotalPages());
	model.addAttribute("totalItems", page.getTotalElements());
	
	model.addAttribute("sortField", sortField);
	model.addAttribute("sortDir", sortDir);
	model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
	model.addAttribute("students", students);
    return "list-students";
}
@RequestMapping
public String getAllStudents(Model model) {
	List<Student> students=studentService.getAllStudent();
	model.addAttribute("students",students);
	return "list-students";
}
@RequestMapping(path= {"/project/{studentId}"})
public String showProjects(Model model,@PathVariable (value="studentId") Integer studentId) {
	List<Project> projects=projectService.getAllProjectsByStudentId(studentId);
	model.addAttribute(b, projects);
	return "list-projects";
}
@RequestMapping(path= {"/create","/edit/{studentId}"})
public String updateStudentById(Model model,@PathVariable (value="studentId") Optional<Integer> studentId) throws StudentNotFoundException {
	if(studentId.isPresent()) {
		Student student=studentService.getStudentById(studentId.get());
		model.addAttribute(a,student);
	}
	else {
		model.addAttribute(a, new Student());
	}
	return "add-edit-student";
}
@RequestMapping(path= {"/createP/{studentId}"})
public String addProject(Model model,@PathVariable (value="projectId") Optional<Integer> projectId,@PathVariable (value="studentId") Integer studentId) throws StudentNotFoundException {
	if(projectId.isPresent()) {
		Project project=projectService.getProjectById(projectId.get());
		model.addAttribute(c, project);
		model.addAttribute("studentId", studentId);
	}
	else {
		model.addAttribute(c, new Project());
		model.addAttribute("studentId", studentId);
	}
	return "add-edit-projects";
}
@RequestMapping(path= {"/editP/{projectId}"})
public String updateProject(Model model,@PathVariable (value="projectId") Optional<Integer> projectId,Integer studentId) throws StudentNotFoundException {
	if(projectId.isPresent()) {
		Project project=projectService.getProjectById(projectId.get());
		model.addAttribute(c, project);
		model.addAttribute("projectId", project.getStudent().getId());
		model.addAttribute("projectId1", project.getProjectId());
	}
	return "edit-projects";
}
@RequestMapping(path="deleteStudent/{studentId}")
public String deleteStudentById(Model model,@PathVariable (value="studentId") Integer studentId) throws StudentNotFoundException {
	studentService.deleteStudentById(studentId);
	return e;
}
@RequestMapping(path="deleteProject/{projectId}")
public String deleteProjectById(Model model,@PathVariable (value="projectId") Integer projectId) throws StudentNotFoundException {
	Project project=projectService.getProjectById(projectId);
	int id=project.getStudent().getId();
	projectService.deleteProject(projectId, project.getStudent().getId());
	return d+id;
}
@RequestMapping(path="/createStudent",method=RequestMethod.POST)
public String createStudent(Student student,@RequestParam("image") MultipartFile multipartFile) throws IOException {
	String fileName=StringUtils.cleanPath(multipartFile.getOriginalFilename());
	student.setPhotos(fileName);
	Student savedStudent=studentRepository.save(student);
	studentService.createOrUpdateStudent(student);
	String uploadDir="students-photos/"+savedStudent.getId();
	FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
	 return e;
}
@RequestMapping(path="/user-id",method=RequestMethod.GET)
public String idTest(Model model,@RequestParam (value = "id", required = false) Integer id) throws StudentNotFoundException {
	
	boolean b=false;
	if(studentService.getStudentById(id)==null) {
		b=false;
		model.addAttribute(a,new Student());
	}
	else {
		b=true;
		model.addAttribute(a,studentService.getStudentById(id));
	}
	model.addAttribute("value",b);
	return "user-id";
}
@RequestMapping(path="/updateProject/{studentId}/{projectId}",method=RequestMethod.POST)
public String createProject(Project project , @PathVariable (value="studentId") Integer studentId,@PathVariable (value="projectId") Integer projectId) throws StudentNotFoundException {
	projectService.updateProject(projectId, studentId, project);
	return d+studentId;
}
@RequestMapping(path="/createProject/{studentId}",method=RequestMethod.POST)
public String createProject(Project projects , @PathVariable (value="studentId") Integer studentId) throws StudentNotFoundException {
	projectService.createProject(studentId, projects);
	return d+studentId;
}
}

