package com.poc8.students;
import java.io.IOException;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;
@RestController
@RequestMapping("/students")
public class StudentController {
	ObjectMapper objectMapper=new ObjectMapper();
	@Autowired
	private StudentService studentService;
	@GetMapping("/getAllStudents")
	public ResponseModule getAllStudents(){
		return studentService.getAllStudentsTest();
	}
	@GetMapping("/getStudentById/{studentId}")
	public ResponseModule getStudentById(@Valid @PathVariable (value="studentId") Integer studentId){
		return studentService.getStudentByIdTest(studentId);
	}
	@PostMapping("/createStudent")
	public ResponseModule createStudent1(@Valid @RequestParam(value="id") String id,@Valid @RequestParam("fname") String fname,@Valid @RequestParam("lname") String lname,@Valid @RequestParam("email") String email,@Valid @RequestParam("contact") String contact,@RequestParam("image") MultipartFile multipartFile) throws IOException  {
		String fileName=StringUtils.cleanPath(multipartFile.getOriginalFilename());
		Student studentRequest=new Student();
		studentRequest.setId(Integer.parseInt(id));
		studentRequest.setFname(fname.substring(0, fname.length()));
		studentRequest.setLname(lname.substring(0, lname.length()));
		studentRequest.setContact(contact.substring(0, contact.length()));
		studentRequest.setEmail(email.substring(0, email.length()));
		studentRequest.setPhotos(fileName);
		String uploadDir="students-photos/"+studentRequest.getId();
		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		return studentService.createTest(studentRequest);
	}
	@PostMapping("/updateStudent")
	public ResponseModule updateStudent(@Valid  @RequestParam(value="id") String id,@Valid @RequestParam("fname") String fname,@Valid @RequestParam("lname") String lname,@Valid @RequestParam("email") String email,@Valid @RequestParam("contact") String contact,@RequestParam("image") MultipartFile multipartFile) throws IOException  {
		String fileName=StringUtils.cleanPath(multipartFile.getOriginalFilename());
		Student studentRequest=new Student();
		studentRequest.setId(Integer.parseInt(id));
		studentRequest.setFname(fname.substring(0, fname.length()));
		studentRequest.setLname(lname.substring(0, lname.length()));
		studentRequest.setContact(contact.substring(0, contact.length()));
		studentRequest.setEmail(email.substring(0, email.length()));
		studentRequest.setPhotos(fileName);
		String uploadDir="students-photos/"+studentRequest.getId();
		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		return studentService.updateStudent(studentRequest, studentRequest.getId());
	}
	@DeleteMapping("/deleteStudent/{studentId}")
	public ResponseModule deleteStudent(@Valid @PathVariable (value="studentId") Integer studentId){
		return studentService.deleteStudentByIdTest(studentId);
	}
}
